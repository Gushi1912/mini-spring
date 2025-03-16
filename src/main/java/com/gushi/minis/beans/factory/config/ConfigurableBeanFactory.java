package com.gushi.minis.beans.factory.config;

import com.gushi.minis.beans.factory.BeanFactory;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 10:33
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    int getBeanPostProcessorCount();

    void registerDependentBean(String beanName, String dependentBeanName);

    String[] getDependentBeans(String name);

    String[] getDependenciesForBean(String beanName);
}
