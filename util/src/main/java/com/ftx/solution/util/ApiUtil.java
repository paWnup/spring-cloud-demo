package com.ftx.solution.util;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author puan
 * @date 2019-04-04 15:28
 **/
@Log4j
public class ApiUtil {

    public static void main(String[] args) {
        String url = "http://47.95.118.118:9091/v2/api-docs";
        try {
            String json = HttpUtils.getInstance().executeGet(url);
            Gson gson = new Gson();
            Map<String, Object> map = gson.fromJson(json, HashMap.class);
            DocUtil.createDoc(map, "C:/Users/Administrator/Desktop/接口文档.doc");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
