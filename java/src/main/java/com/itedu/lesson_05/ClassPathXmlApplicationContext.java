package com.itedu.lesson_05;

import com.itedu.lesson_05.entity.UserEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.List;

public class ClassPathXmlApplicationContext {

    private String xmlPath;

    public ClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId) throws DocumentException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 问：spring加载过程 或者 spring ioc 实现原理？
        /*
        1. 读取xml的配置文件
        2. 获取到每个bean配置，获取class地址
        3. 获取到class地址，进行反射实例化对象，使用反射api为私有属性赋值
         */

        // 1. 读取xml的配置文件
        // 获取xml解析器
        SAXReader saxReader = new SAXReader();
        // this.getClass().getClassLoader().getResourceAsStream("xmlPath") 获取当前项目路径
        Document document = saxReader.read(this.getClass().getClassLoader().getResourceAsStream(xmlPath));
        // 获取到根节点对象
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();

        Object obj = null;

        for (Element sonEle : elements) {
            // 2. 获取到每个bean配置，获取class地址
            String sonBeanId = sonEle.attributeValue("id");
            if (!beanId.equals(sonBeanId)) {
                continue;
            }
            String beanClassPath = sonEle.attributeValue("class");
            // 3. 获取到class地址，进行反射实例化对象，使用反射api为私有属性赋值
            Class<?> forName = Class.forName(beanClassPath);
            obj = forName.newInstance();
            // 拿到成员属性
            List<Element> sonSonEle = sonEle.elements();
            for (Element element : sonSonEle) {
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");

                // 使用反射api为私有属性赋值
                Field declaredField = forName.getDeclaredField(name);
                // 允许给私有属性赋值
                declaredField.setAccessible(true);
                declaredField.set(obj, value);
            }
        }

        return obj;
    }

    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DocumentException, NoSuchFieldException {

        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("user.xml");
        Object bean = application.getBean("user1");
        UserEntity user = (UserEntity) bean;
        System.out.println(user.getUserId() + "---" + user.getUserName());
    }
}
