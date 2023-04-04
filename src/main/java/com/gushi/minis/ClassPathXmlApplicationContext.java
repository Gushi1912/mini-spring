package com.gushi.minis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2023/4/4 14:36
 */
public class ClassPathXmlApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>(16);

    private Map<String, Object> singletonObejcts = new HashMap<>(16);

    /**
     * 构造器，获取外部配置文件，并解析
     * @param fileName
     */
    public ClassPathXmlApplicationContext(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    /**
     * 解析xml配置文件，读取bean的配置信息并装配
     * @param fileName
     */
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();

            //对配置文件中的每个bean进行处理
            for (Element element : rootElement.elements()) {
                //获取bean的基本信息
                String beanId = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);

                //将bean的定义存放到beanDefinitions中
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }


    private void instanceBeans() {
//        beanDefinitions.forEach();
        beanDefinitions.forEach(beanDefinition -> {

            try {
                singletonObejcts.put(beanDefinition.getId(),
                        Class.forName(beanDefinition.getClassName()).getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                //TODO 集成基本的日志系统
                throw new RuntimeException(e);
            }
        });
    }


    public Object getBean(String beanName) {
        return singletonObejcts.get(beanName);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Object aService = classPathXmlApplicationContext.getBean("aService");
        System.out.println(aService);
    }


}
