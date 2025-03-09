package com.gushi.minis.beans;

import com.gushi.minis.core.Resource;
import org.dom4j.Element;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/6 15:15
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory) {
        this.simpleBeanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            this.simpleBeanFactory.registerBeanDefinition(beanId, beanDefinition);

            //处理属性
        }
    }
}
