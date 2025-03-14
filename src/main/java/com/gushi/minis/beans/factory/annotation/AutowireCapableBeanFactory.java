package com.gushi.minis.beans.factory.annotation;

import com.gushi.minis.beans.BeansException;
import com.gushi.minis.beans.factory.BeanFactory;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 11:09
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;

    Object applyBeanPostProcessorBeforeInitialization(Object existBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorAfterInitialization(Object existBean, String beanName) throws BeansException;
}
