package com.gushi.minis.context;

import com.gushi.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.gushi.minis.beans.BeansException;
import com.gushi.minis.beans.factory.config.ConfigurableBeanFactory;
import com.gushi.minis.beans.factory.ListableBeanFactory;
import com.gushi.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.gushi.minis.core.env.Environment;
import com.gushi.minis.core.env.EnvironmentCapable;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 14:51
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher {

    String getApplicationName();
    long getStartupDate();
    ConfigurableListableBeanFactory getBeanFactory() throws BeansException;
    void setEnvironment(Environment environment);
    Environment getEnvironment();
    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);
    void refresh() throws BeansException, IllegalStateException;
    void close();
    boolean isActive();
}
