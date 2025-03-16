package com.gushi.minis.context;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 9:47
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);

    void addApplicationListener(ApplicationListener listener);
}
