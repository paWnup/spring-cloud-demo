package com.ftx.solution.util;

import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author puan
 * @date 2019-03-9:
 **/
@Log4j
public class MyFrame {

    public static void main(String[] args) {
        String url = "jdbc:mysql://47.95.118.118:3306/demo";
        String username = "root";
        String password = "123456";
        String schema = "demo";
        Map<String, Object> map = getDatabaseConstructureMap(url, username, password, schema);
        DocUtil.createDoc(map, "database_tables", "C:/Users/Administrator/Desktop/数据库表结构文档.doc");
    }

    private static Map<String, Object> getDatabaseConstructureMap(String url, String username, String password, String schema) {
        Connection connection = getConnection(url, username, password);
        Map<String, Object> map = new HashMap<>();
        List<String> tables = getTables(connection, schema);
        List<Map<String, String>> list = new ArrayList<>();
        for (String table : tables) {
            Map table1 = new HashMap(2);
            table1.put("tableName", table);
            List<Map<String, String>> columns = getTableColumns(connection, table, schema);
            table1.put("columns", columns);
            list.add(table1);
        }
        map.put("maps", list);
        return map;
    }

    private static List<Map<String, String>> getTableColumns(Connection connection, String table, String schema) {
        ResultSet rs = null;
        try {
            String sql = "SELECT COLUMN_NAME columnName, COLUMN_TYPE columnType,"
                    + "COLUMN_KEY  columnKey, IS_NULLABLE isNullable,"
                    + "COLUMN_DEFAULT columnDefault, COLUMN_COMMENT columnComment"
                    + " from INFORMATION_SCHEMA.COLUMNS where table_schema = '"
                    + schema
                    + "' AND table_name = '"
                    + table
                    + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            List<Map<String, String>> tables = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("columnName", rs.getString("columnName"));
                map.put("columnType", rs.getString("columnType"));
                map.put("columnKey", rs.getString("columnKey"));
                map.put("isNullable", rs.getString("isNullable"));
                map.put("columnDefault", rs.getString("columnDefault"));
                map.put("columnComment", rs.getString("columnComment"));
                tables.add(map);
            }
            rs.close();
            return tables;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("获取数据库表名列表异常");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    private static List<String> getTables(Connection connection, String schema) {
        ResultSet rs = null;
        try {
            String sql = "show tables from " + schema;
            PreparedStatement statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            List<String> tables = new ArrayList<>();
            String columnLabel = "Tables_in_" + schema;
            while (rs.next()) {
                tables.add(rs.getString(columnLabel));
            }
            rs.close();
            return tables;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("获取数据库表名列表异常");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    private static Connection getConnection(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("连接异常");
        }
    }
}
