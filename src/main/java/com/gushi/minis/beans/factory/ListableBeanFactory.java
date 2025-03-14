package com.gushi.minis.beans.factory;

import com.gushi.minis.beans.BeansException;

import java.util.Map;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 10:23
 */
public interface ListableBeanFactory extends BeanFactory{

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;
}
