package com.gushi.minis.beans;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 23:13
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
