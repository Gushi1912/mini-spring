package com.gushi.minis.beans.factory.annotation;

import com.gushi.minis.beans.factory.config.BeanPostProcessor;
import com.gushi.minis.beans.BeansException;
import com.gushi.minis.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Field;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/13 22:17
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private AutowireCapableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        Class<?> clz = bean.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                String fieldName = field.getName();
                Object autowiredObj = this.beanFactory.getBean(fieldName);
                try {
                    field.setAccessible(true);
                    field.set(result, autowiredObj);
                    System.out.println("autowire" + fieldName + "for bean " + beanName);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public AutowireCapableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

}
