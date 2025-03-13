package com.gushi.minis.beans;

import com.gushi.minis.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

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

            //处理属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                String pRef = e.attributeValue("ref");
                String pv = "";
                boolean isRef = false;
                if (pValue != null && !pValue.equals("")) {
                    pv = pValue;
                } else if (pRef != null && !pRef.equals("")) {
                    pv = pRef;
                    isRef = true;
                    refs.add(pRef);
                }
                propertyValues.addPropertyValue(new PropertyValue(pType, pName, pv, isRef));
            }
            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setDependsOn(refs.toArray(new String[0]));
            //处理构造器参数
            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues argumentValues = new ArgumentValues();
            for (Element constructorElement : constructorElements) {
                String aType = constructorElement.attributeValue("type");
                String aName = constructorElement.attributeValue("name");
                String aValue = constructorElement.attributeValue("value");
                argumentValues.addArgumentValue(new ArgumentValue(aValue, aType, aName));
            }
            beanDefinition.setConstructorArgumentValues(argumentValues);
            this.simpleBeanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }
}
