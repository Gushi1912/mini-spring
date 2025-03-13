package com.gushi.minis.test.service.impl;

import com.gushi.minis.test.BaseService;
import com.gushi.minis.test.service.BService;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/13 16:20
 */
public class BServiceImpl implements BService {
    private BaseService baseService;

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }
}
