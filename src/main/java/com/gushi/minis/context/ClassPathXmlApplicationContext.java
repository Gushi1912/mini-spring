package com.gushi.minis.context;


import com.gushi.minis.beans.BeansException;
import com.gushi.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.gushi.minis.beans.factory.support.DefaultListableBeanFactory;
import com.gushi.minis.beans.factory.config.AutowireCapableBeanFactory;
import com.gushi.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.gushi.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.gushi.minis.core.ClassPathXmlResource;
import com.gushi.minis.test.service.AService;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2023/4/4 14:36
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

//    SimpleBeanFactory beanFactory;
    DefaultListableBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(fileName);
        DefaultListableBeanFactory autowireCapableBeanFactory = new DefaultListableBeanFactory();
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

    @Override
    void registerListeners() {
        ApplicationListener applicationListener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(applicationListener);
    }

    @Override
    void initApplicationEventPublisher() {
        SimpleApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

    }


    void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    void finishRefresh() {
        publishEvent(new ContextRefreshEvent("context refreshed..."));
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
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
