package com.gushi.minis.context;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 14:50
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher{

    List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publishEvent(ApplicationEvent event) {
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }
}
