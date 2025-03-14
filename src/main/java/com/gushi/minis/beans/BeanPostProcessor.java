package com.gushi.minis.beans;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/13 22:14
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
