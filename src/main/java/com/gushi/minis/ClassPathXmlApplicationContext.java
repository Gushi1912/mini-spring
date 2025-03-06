package com.gushi.minis;


/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2023/4/4 14:36
 */
public class ClassPathXmlApplicationContext implements BeanFactory{

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

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }

    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Object aService = classPathXmlApplicationContext.getBean("aService");
        System.out.println(aService);
    }
}
