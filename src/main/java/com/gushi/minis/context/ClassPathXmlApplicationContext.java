package com.gushi.minis.context;


import com.gushi.minis.beans.BeansException;
import com.gushi.minis.beans.factory.config.AutowireCapableBeanFactory;
import com.gushi.minis.beans.factory.BeanFactory;
import com.gushi.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.gushi.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.gushi.minis.core.ClassPathXmlResource;
import com.gushi.minis.test.service.AService;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2023/4/4 14:36
 */
public class ClassPathXmlApplicationContext implements BeanFactory , ApplicationEventPublisher{

//    SimpleBeanFactory beanFactory;
    AutowireCapableBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(fileName);
//        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        AutowireCapableBeanFactory autowireCapableBeanFactory = new AutowireCapableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(autowireCapableBeanFactory);
        reader.loadBeanDefinitions(classPathXmlResource);
        this.beanFactory = autowireCapableBeanFactory;
        if (isRefresh) {
            try {
                refresh();
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void refresh() throws BeansException, IllegalStateException {
        registerBeanPostProcessor(this.beanFactory);
        onRefresh();
    }

    private void registerBeanPostProcessor(AutowireCapableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    private void onRefresh() {
        this.beanFactory.refresh();
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
        AService aService = (AService) classPathXmlApplicationContext.getBean("aService");
        System.out.println(aService);
    }
}
