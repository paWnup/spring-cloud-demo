package com.ftx.solution.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author puan
 * @date 2019-03-29 14:46
 **/
@Log4j
public class DocUtil {

    /**
     * 根据Doc模板生成word文件
     *
     * @param dataMap  Map 需要填入模板的数据
     * @param fileName 文件名称
     * @param savePath 保存路径
     */
    public static void createDoc(Map<String, Object> dataMap, String fileName, String savePath) {
        Configuration configure = new Configuration();
        configure.setDefaultEncoding("utf-8");
        try {
            //加载模板文件
            configure.setClassForTemplateLoading(DocUtil.class, "/template/");
            //设置对象包装器
            configure.setObjectWrapper(new DefaultObjectWrapper());
            //设置异常处理器
            configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            //定义Template对象,注意模板类型名字与downloadType要一致
            Template template = configure.getTemplate(fileName + ".ftl");
            //输出文档
            File outFile = new File(savePath);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8));
            template.process(dataMap, out);
            outFile.delete();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("导出失败");
        }
    }
}
