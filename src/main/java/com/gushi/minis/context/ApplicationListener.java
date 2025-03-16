package com.gushi.minis.context;

import java.util.EventListener;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 14:47
 */
public class ApplicationListener implements EventListener {

    void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }

}
