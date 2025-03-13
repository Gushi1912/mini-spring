package com.gushi.minis.test.service.impl;

import com.gushi.minis.test.service.AService;
import com.gushi.minis.test.service.BService;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2023/4/4 16:09
 */
public class AServiceImpl implements AService {

    private String name;

    private int level;

    private String property1;
    private String property2;

    private BService bService;


    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }


    @Override
    public void sayHello() {
        System.out.println(this.property1 + "," + this.property2);
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public BService getBService() {
        return bService;
    }

    public void setBService(BServiceImpl bService) {
        this.bService = bService;
    }

    @Override
    public String toString() {
        return "AServiceImpl{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", property1='" + property1 + '\'' +
                ", property2='" + property2 + '\'' +
                ", bService=" + bService +
                '}';
    }
}
