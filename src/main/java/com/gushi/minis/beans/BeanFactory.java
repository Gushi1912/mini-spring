package com.gushi.minis.beans;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/6 14:12
 */
public interface BeanFactory {
    /**
     * 更具bean名称获取bean实例
     * @param beanName bean名称
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 是否包含bean
     * @param name
     * @return
     */
    boolean containsBean(String name);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}
