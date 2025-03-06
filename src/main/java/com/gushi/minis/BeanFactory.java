package com.gushi.minis;

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
     * 向beanDefinitionMap中注册beanDefinition
     * @param beanDefinition
     */
    void registerBeanDefinition(BeanDefinition beanDefinition);
}
