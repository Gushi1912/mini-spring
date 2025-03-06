package com.gushi.minis;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/6 15:24
 */
public class SimpleBeanFactory implements BeanFactory{

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private List<String> beanNames = new ArrayList<>();

    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 容器核心方法
     * @param beanName bean名称
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        //尝试获取bean实例
        Object singleton = singletonObjects.get(beanName);
        //如果此时没有这个Bean实例，则获取定义来创建实例
        if (singleton == null) {
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                throw new BeansException("can't find bean definition");
            } else {
                BeanDefinition beanDefinition = beanDefinitions.get(i);
                try {
                    singleton = Class.forName(beanDefinition.getClassName()).getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException | ClassNotFoundException e) {

                }
                singletonObjects.put(beanName, singleton);
            }
        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
