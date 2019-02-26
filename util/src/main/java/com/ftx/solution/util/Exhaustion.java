package com.ftx.solution.util;

import lombok.Data;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 穷举法计算
 * 需要税额这一栏相加等于2418713.9的数组，把符合要求的数据做标记
 *
 * @author puan
 * @date 2019-01-11 10:13
 **/
public class Exhaustion {

    private static BigDecimal GOAL = new BigDecimal("2418713.9");

    private static Set<Long> USED_TAX = new HashSet<>(765);

    private static int COUNT = 0;

    private static int MAX_INDEX = 846;

    private static List<Tax> TAX = new ArrayList<>(880);

    private static boolean flag = true;

    private static boolean success = false;

    public static void main(String[] args) throws IOException {
        String fileName = "C:\\work\\idea workspace\\solution\\util\\src\\main\\resources\\test.xlsx";
        InputStream is = new FileInputStream(new File(fileName));
        Workbook hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        // 循环工作表Sheet，封装成List
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            TAX = createTaxList(hssfSheet);
        }
        //计算
        hssfWorkbook = caculate();
        fileName = "C:\\work\\idea workspace\\solution\\util\\src\\main\\resources\\test_result.xlsx";
        OutputStream out = new FileOutputStream(new File(fileName));
        if (hssfWorkbook != null) {
            hssfWorkbook.write(out);
        }
        out.flush();
        out.close();
    }

    private static List<Tax> createTaxList(Sheet hssfSheet) {
        List<Tax> taxes = new ArrayList<>(900);
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            Tax tax = new Tax();
            Row hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow != null) {
                tax.setCompany(hssfRow.getCell(0).toString());
                tax.setPrint(hssfRow.getCell(1).toString());
                tax.setCode(hssfRow.getCell(2).toString());
                tax.setFph(hssfRow.getCell(3).toString());
                tax.setJbf(hssfRow.getCell(4).toString());
                tax.setSe(hssfRow.getCell(5).getNumericCellValue());
                tax.setPrintDate(hssfRow.getCell(6).toString());
                taxes.add(tax);
            }
        }
        Collections.sort(taxes);
        return taxes;
    }

    private static XSSFWorkbook caculate() {
        int max = max();
        int min = min();
        BigDecimal rest = GOAL;
        try {
            for (int i = min; i <= max; i++) {
                COUNT = i;
                getSubArray(rest, 0);
                if (success) {
                    break;
                }
            }
            return export();
        } catch (Exception e) {
            e.printStackTrace();
            USED_TAX = new HashSet<>(765);
            flag = true;
            COUNT = 0;
            TAX = new ArrayList<>(880);
        }
        return null;
    }

    private static void getSubArray(BigDecimal rest, int index) {
        Tax tax = TAX.get(index);
        boolean jump = false;
        BigDecimal se = BigDecimal.valueOf(tax.getSe());
        System.out.println("rest:" + rest + "  se:" + se + "  index:" + index);
        if (rest.equals(se)) {
            USED_TAX.add(tax.getId());
            success = true;
            System.out.println("----success----");
        } else if (rest.min(se).equals(rest) && index < MAX_INDEX) {
            jump = true;
            getSubArray(rest, index + 1);
        } else if (USED_TAX.size() < COUNT && index < MAX_INDEX) {
            rest = rest.subtract(se);
            USED_TAX.add(tax.getId());
            getSubArray(rest, index + 1);
        } else {
            flag = false;
            return;
        }
        if (!jump && !flag) {
            USED_TAX.remove(tax.getId());
            rest = rest.add(se);
            if (index == MAX_INDEX) {
                return;
            }
            flag = true;
            getSubArray(rest, ++index);
        }
    }

    private static int min() {
        return getCount(TAX);
    }

    private static int max() {
        List<Tax> maxTax = new ArrayList<>(TAX);
        Collections.reverse(maxTax);
        return getCount(maxTax);
    }

    private static int getCount(List<Tax> taxes) {
        BigDecimal x = GOAL;
        int count = 0;
        for (Tax tax : taxes) {
            BigDecimal thisTax = BigDecimal.valueOf(tax.getSe());
            x = x.subtract(thisTax);
            count++;
            if (x.min(BigDecimal.ZERO).equals(x)) {
                break;
            }
        }
        return count;
    }

    private static XSSFWorkbook export() {
        //创建HSSFWorkbook对象(excel的文档对象)
        XSSFWorkbook wb = new XSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        XSSFSheet sheet = wb.createSheet("success");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        for (int i = 0; i < TAX.size(); i++) {
            Tax tax = TAX.get(i);
            XSSFRow row = sheet.createRow(i);
            generateCell(row, 0, XSSFCell.CELL_TYPE_NUMERIC, tax.getCompany());
            generateCell(row, 1, XSSFCell.CELL_TYPE_STRING, tax.getPrint());
            generateCell(row, 2, XSSFCell.CELL_TYPE_NUMERIC, tax.getCode());
            generateCell(row, 3, XSSFCell.CELL_TYPE_NUMERIC, tax.getFph());
            generateCell(row, 4, XSSFCell.CELL_TYPE_NUMERIC, tax.getJbf());
            generateCell(row, 5, XSSFCell.CELL_TYPE_STRING, String.valueOf(tax.getSe()));
            row.createCell(6).setCellValue(tax.getPrintDate());
            if (USED_TAX.contains(tax.getId())) {
                row.createCell(7).setCellValue("YES");
            }
        }
        return wb;
    }

    private static void generateCell(XSSFRow row, int i, int cellType, String calue) {
        XSSFCell cell = row.createCell(i);
        cell.setCellType(cellType);
        cell.setCellValue(calue);
    }

    @Data
    private static class Tax implements Comparable<Tax> {
        private long id;
        /**
         * 公司
         */
        private String company;
        /**
         * 打印顺序号
         */
        private String print;
        /**
         * 发票代码
         */
        private String code;
        /**
         * 发票号
         */
        private String fph;

        /**
         * 净保费
         */
        private String jbf;

        /**
         * 税额
         */
        private double se;

        /**
         * 打印日期
         */
        private String printDate;

        @Override
        public int compareTo(Tax o) {
            return Double.compare(o.se, this.se);
        }
    }


}
