package com.gushi.minis.beans;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 23:48
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String name, BeanDefinition bd);

    void removeBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);
}
