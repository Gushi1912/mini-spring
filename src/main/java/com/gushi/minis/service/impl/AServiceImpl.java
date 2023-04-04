package com.gushi.minis.service.impl;

import com.gushi.minis.service.AService;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2023/4/4 16:09
 */
public class AServiceImpl implements AService {
    @Override
    public void sayHello() {
        System.out.println("a service 1 say hello");
    }
}
