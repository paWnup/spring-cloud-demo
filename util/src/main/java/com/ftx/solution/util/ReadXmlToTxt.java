package com.ftx.solution.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * 读取xml文件
 * @author puan
 * @date 2019-01-05 12:07
 **/
public class ReadXmlToTxt {
    //编码
    private static final String BM = "BM";
    private static final String PID = "PID";
    private static final String MC = "MC";
    private static final String KZ1 = "KZ1";
    private static final String JM = "JM";
    private static final String NSRSBH = "NSRSBH";
    private static final String DZ = "DZ";
    private static final String YHZH = "YHZH";
    private static final String YJDZ = "YJDZ";
    private static final String O_BM = "O_BM";

    public static void main(String[] args) {
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            StringBuilder txt = new StringBuilder();
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("C:\\Users\\30256\\Desktop\\134204.xml"));
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            Element hkxx = bookStore.element("KHXX");
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = hkxx.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            int i = 1;
            while (it.hasNext()) {
                Element row = (Element) it.next();
                // 获取book的属性名以及 属性值
                List<Attribute> rowAttrs = row.attributes();
                if (!StringUtils.isEmpty(row.attribute(NSRSBH).getValue())) {
                    txt.append(format(i++))
                            .append(",")
                            .append(row.attribute(MC).getValue())
                            .append(",")
                            .append(row.attribute(JM).getValue())
                            .append(",")
                            .append(row.attribute(NSRSBH).getValue())
                            .append(",")
                            .append(row.attribute(DZ).getValue())
                            .append(",")
                            .append(row.attribute(YHZH).getValue())
                            .append(",")
                            .append(row.attribute(YJDZ).getValue())
                            .append(",")
                            .append(row.attribute(O_BM).getValue())
                            .append(",")
                            .append("False")
                            .append("\n");
                }
            }
            System.out.println(txt.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private static String format(int i) {
        if (i < 10) {
            return "00" + i;
        } else if (i < 100) {
            return "0" + i;
        } else {
            return String.valueOf(i);
        }
    }
}
