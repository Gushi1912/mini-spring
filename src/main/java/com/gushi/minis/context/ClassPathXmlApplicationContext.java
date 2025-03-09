package com.gushi.minis.context;


import com.gushi.minis.beans.BeansException;
import com.gushi.minis.beans.BeanDefinition;
import com.gushi.minis.beans.BeanFactory;
import com.gushi.minis.beans.SimpleBeanFactory;
import com.gushi.minis.beans.XmlBeanDefinitionReader;
import com.gushi.minis.core.ClassPathXmlResource;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2023/4/4 14:36
 */
public class ClassPathXmlApplicationContext implements BeanFactory , ApplicationEventPublisher{

    BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(fileName);
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(simpleBeanFactory);
        reader.loadBeanDefinitions(classPathXmlResource);
        this.beanFactory = simpleBeanFactory;
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    public boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public void publishEvent(ApplicationEvent event) {

    }

    public boolean isSingleton(String name) {
        return false;
    }

    public boolean isPrototype(String name) {
        return false;
    }

    public Class<?> getType(String name) {
        return null;
    }


    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Object aService = classPathXmlApplicationContext.getBean("aService");
        System.out.println(aService);
    }
}
