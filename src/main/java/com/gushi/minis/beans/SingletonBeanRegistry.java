package com.gushi.minis.beans;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 9:09
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containSingleton(String beanName);

    String[] getSingletonNames();
}
