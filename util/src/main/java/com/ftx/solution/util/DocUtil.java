package com.ftx.solution.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.log4j.Log4j;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        }
    }

    /**
     * POI生成word文件
     *
     * @param dataMap  Map 需要填入模板的数据
     * @param savePath 保存路径
     */
    public static void createDoc(Map<String, Object> dataMap, String savePath) {
        //Blank Document
        XWPFDocument document = buildDocument(dataMap);
        //Write the Document in file system
        try (FileOutputStream out = new FileOutputStream(new File(savePath))) {
            document.write(out);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private static XWPFDocument buildDocument(Map<String, Object> dataMap) {
        XWPFDocument document = new XWPFDocument();
        Map<String, Object> info = (Map<String, Object>) dataMap.get("info");
        createTitle((String) info.get("title"), document.createParagraph());
        createContent(document, dataMap);
        return document;
    }

    private static void createContent(XWPFDocument document, Map<String, Object> dataMap) {
        List<Object> tags = (List<Object>) dataMap.get("tags");
        for (int i = 0; i < tags.size(); i++) {
            Map<String, String> tagMap = (Map<String, String>) tags.get(i);
            String tagDesc = (i + 1) + " " + tagMap.get("description");
            addParagraph(document, tagDesc, ParagraphAlignment.LEFT, 16, 0, true);
            String tagName = tagMap.get("name");
            Map<String, Object> paths = (Map<String, Object>) dataMap.get("paths");
            Set<String> pathsKeys = paths.keySet();
            int j = 1;
            for (String path : pathsKeys) {
                Map<String, Object> methods = (Map<String, Object>) paths.get(path);
                Set<String> methodsKeys = methods.keySet();
                for (String method : methodsKeys) {
                    Map<String, Object> methodMap = (Map<String, Object>) methods.get(method);
                    List<String> tagList = (List<String>) methodMap.get("tags");
                    if (tagList.get(0).equals(tagName)) {
                        String summary = ((i + 1) + "." + j++) + " " + (String) methodMap.get("summary");
                        addParagraph(document, summary, ParagraphAlignment.LEFT, 14, 0, true);

                        //url
                        addParagraph(document, "URL：" + path, ParagraphAlignment.LEFT, 14, 10);

                        //method
                        addParagraph(document, "请求方式：" + method, ParagraphAlignment.LEFT, 14, 10);

                        //parameters
                        addParagraph(document, "请求参数：", ParagraphAlignment.LEFT, 14, 10);

                        List<Object> params = (List<Object>) methodMap.get("parameters");
                        if (params != null && params.size() > 0) {
                            createParamTable(document, params);
                        }

                        //response content type
                        String responseType = "响应类型：" + ((List<String>) methodMap.get("produces")).get(0);
                        addParagraph(document, responseType, ParagraphAlignment.LEFT, 14, 10);

                        //response
                        addParagraph(document, "响应数据：", ParagraphAlignment.LEFT, 14, 10);

                        Map<String, Object> definitions = (Map<String, Object>) dataMap.get("definitions");
                        String voName = getVoNameStep1(methodMap);
                        if (definitions != null && !definitions.isEmpty() && !StringUtils.isEmpty(voName)) {
                            createResponseTable(document, definitions, voName);
                        }
                    }
                }
            }
        }
    }

    /**
     * 增加段落
     *
     * @param document     文档对象
     * @param text         段落内容
     * @param alignment    对齐方式
     * @param fontSize     字体大小
     * @param textPosition
     */
    private static void addParagraph(XWPFDocument document, String text,
                                     ParagraphAlignment alignment, int fontSize, int textPosition, boolean bold) {
        XWPFParagraph paragraph = document.createParagraph();
        addParagraph(paragraph, text, alignment, fontSize, textPosition, bold);
    }

    private static XWPFParagraph addParagraph(XWPFParagraph paragraph, String text,
                                              ParagraphAlignment alignment, int fontSize, int textPosition, boolean bold) {
        paragraph.setAlignment(alignment);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontSize(fontSize);
        run.setFontFamily("Times New Roman");
        run.setTextPosition(textPosition);
        run.setBold(bold);
        return paragraph;
    }

    /**
     * 增加段落
     *
     * @param document     文档对象
     * @param text         段落内容
     * @param alignment    对齐方式
     * @param fontSize     字体大小
     * @param textPosition
     */
    private static void addParagraph(XWPFDocument document, String text,
                                     ParagraphAlignment alignment, int fontSize, int textPosition) {
        addParagraph(document, text, alignment, fontSize, textPosition, false);
    }

    private static String getVoNameStep1(Map<String, Object> methodMap) {
        Map<String, Object> responses = (Map<String, Object>) methodMap.get("responses");
        Map<String, Object> response = (Map<String, Object>) responses.get("200");
        Map<String, Object> schema = (Map<String, Object>) response.get("schema");
        return getVoNameStep2(schema);
    }

    private static String getVoNameStep2(Map<String, Object> schema) {
        if (schema == null) {
            return null;
        }
        String name = (String) schema.get("$ref");
        if (StringUtils.isEmpty(name)) {
            Map<String, String> items = (Map<String, String>) schema.get("items");
            if (items == null) {
                name = "";
            } else {
                name = items.get("$ref");
            }
        }
        return name;
    }

    /**
     * 响应数据表格
     *
     * @param document
     * @param definitions
     * @param voName
     */
    private static void createResponseTable(XWPFDocument document, Map<String, Object> definitions, String voName) {
        XWPFTable responseTable = document.createTable();

        //表格第一行
        XWPFTableRow row = responseTable.getRow(0);
        setCell(row, 0, "名称", "3600", ParagraphAlignment.CENTER, true);
        createCell(row, "描述", "3600", ParagraphAlignment.CENTER, true);
        createCell(row, "数据类型", "1000", ParagraphAlignment.CENTER, true);

        Set<String> names = definitions.keySet();
        //从第二行开始
        for (String name : names) {
            if (("#/definitions/" + name).equals(voName)) {
                Map<String, Object> vo = (Map<String, Object>) definitions.get(name);
                Map<String, Object> properties = (Map<String, Object>) vo.get("properties");
                Set<String> proNames = properties.keySet();
                for (String proName : proNames) {
                    Map<String, Object> pro = (Map<String, Object>) properties.get(proName);
                    row = responseTable.createRow();
                    setCell(row, 0, proName, "3600", ParagraphAlignment.LEFT, false);
                    setCell(row, 1, (String) pro.get("description"), "3600", ParagraphAlignment.LEFT, false);
                    String ref = getVoNameStep2(pro);
                    if (!StringUtils.isEmpty(ref)) {
                        setCell(row, 2, "object", "1000", ParagraphAlignment.LEFT, false);
                        createResponseTableRow(responseTable, definitions, ref);
                    } else {
                        setCell(row, 2, (String) pro.get("type"), "1000", ParagraphAlignment.LEFT, false);
                    }
                }
            }
        }
    }

    private static void createResponseTableRow(XWPFTable table, Map<String, Object> definitions, String voName) {
        Set<String> names = definitions.keySet();
        for (String name : names) {
            if (("#/definitions/" + name).equals(voName)) {
                Map<String, Object> vo = (Map<String, Object>) definitions.get(name);
                Map<String, Object> properties = (Map<String, Object>) vo.get("properties");
                Set<String> proNames = properties.keySet();
                for (String proName : proNames) {
                    Map<String, Object> pro = (Map<String, Object>) properties.get(proName);
                    XWPFTableRow row = table.createRow();
                    setCell(row, 0, "", "600", ParagraphAlignment.LEFT, false);
                    setCell(row, 1, proName, "3000", ParagraphAlignment.LEFT, false);
                    setCell(row, 2, (String) pro.get("description"), "3600", ParagraphAlignment.LEFT, false);
                    String ref = getVoNameStep2(pro);
                    if (!StringUtils.isEmpty(ref)) {
                        createCell(row, "object", "1000", ParagraphAlignment.LEFT, false);
                    } else {
                        createCell(row, (String) pro.get("type"), "1000", ParagraphAlignment.LEFT, false);
                    }
                }
            }
        }
    }

    /**
     * 参数表格
     *
     * @param document
     * @param params
     */
    private static void createParamTable(XWPFDocument document, List<Object> params) {
        XWPFTable paramTable = document.createTable();
        //表格第一行
        XWPFTableRow row = paramTable.getRow(0);
        setCell(row, 0, "名称", "2600", ParagraphAlignment.CENTER, true);
        createCell(row, "描述", "2600", ParagraphAlignment.CENTER, true);
        createCell(row, "数据类型", "1000", ParagraphAlignment.CENTER, true);
        createCell(row, "参数类型", "1000", ParagraphAlignment.CENTER, true);
        createCell(row, "是否必填", "1000", ParagraphAlignment.CENTER, true);

        //从第二行开始
        for (Object param : params) {
            Map<String, Object> paramMap = (Map<String, Object>) param;
            row = paramTable.createRow();
            setCell(row, 0, (String) paramMap.get("name"), "2600", ParagraphAlignment.LEFT, false);
            setCell(row, 1, (String) paramMap.get("description"), "2600", ParagraphAlignment.LEFT, false);
            setCell(row, 2, (String) paramMap.get("type"), "1000", ParagraphAlignment.LEFT, false);
            setCell(row, 3, (String) paramMap.get("in"), "1000", ParagraphAlignment.LEFT, false);
            String required = (Boolean) paramMap.get("required") ? "是" : "否";
            setCell(row, 4, required, "1000", ParagraphAlignment.LEFT, false);
        }
    }

    private static void setCell(XWPFTableRow row, int index, String text, String width, ParagraphAlignment alignment, boolean bold) {
        XWPFTableCell cell = row.getCell(index);
        setCellWidthAndVAlign(cell, width, STVerticalJc.CENTER);
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        addParagraph(paragraph, text, alignment, 12, 0, bold);
    }

    private static void createCell(XWPFTableRow row, String text, String width, ParagraphAlignment alignment, boolean bold) {
        XWPFTableCell cell = row.createCell();
        setCellWidthAndVAlign(cell, width, STVerticalJc.CENTER);
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        addParagraph(paragraph, text, alignment, 12, 0, bold);
    }

    /**
     * 设置列宽和垂直对齐方式
     *
     * @param cell     要设置的单元格
     * @param width    宽度
     * @param typeEnum 垂直对齐方式
     */
    private static void setCellWidthAndVAlign(XWPFTableCell cell, String width, STVerticalJc.Enum typeEnum) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewVAlign().setVal(typeEnum);
        CTTblWidth tblWidth = cellPr.isSetTcW() ? cellPr.getTcW() : cellPr.addNewTcW();
        if (!StringUtils.isEmpty(width)) {
            tblWidth.setW(new BigInteger(width));
        }
        tblWidth.setType(STTblWidth.DXA);
    }

    private static void createTitle(String title, XWPFParagraph titleParagraph) {
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText(title);
        titleParagraphRun.setFontSize(20);
        titleParagraphRun.setBold(true);
    }
}
