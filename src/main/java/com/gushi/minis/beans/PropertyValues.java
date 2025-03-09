package com.gushi.minis.beans;

import java.util.List;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 23:29
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList;

    public PropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValueList = propertyValues;
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }

    public int size() {
        return this.propertyValueList.size();
    }

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public void addPropertyValue(String propertyName, Object propertyValue) {
        addPropertyValue(new PropertyValue(propertyName, propertyValue));
    }

    public void removePropertyValue(PropertyValue pv) {
        this.propertyValueList.remove(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[size()]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

    public Object get(String propertyName) {
        PropertyValue pv = getPropertyValue(propertyName);
        return pv == null ? null : pv.getValue();
    }

    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }
}
