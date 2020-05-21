package com.itedu.lesson_05;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * 使用xml进行解析
 */
public class XmlTest {

    static String fileName = "E:\\myStudyProject\\language\\java\\src\\main\\java\\com\\itedu\\lesson_05\\stu.xml";

    public static void main(String[] args) throws DocumentException {

        // xml解析器
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(fileName));

        // 拿到根节点
        Element rootElement = document.getRootElement();

        getNodes(rootElement);
    }

    public static void getNodes(Element rootElement) {

        System.out.println("节点名称: " + rootElement.getName());

        // 拿到节点属性
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性: " + attribute.getName() + "---" + attribute.getText());
        }

        // 节点名称
        if (!rootElement.getTextTrim().equals("")) {
            System.out.println(rootElement.getName() + "---" + rootElement.getText());
        }

        // 返回下一个节点
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            // 获取当前节点值
            Element next = elementIterator.next();
            getNodes(next);
        }
    }
}
