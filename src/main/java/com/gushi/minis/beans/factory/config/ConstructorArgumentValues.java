package com.gushi.minis.beans.factory.config;

import com.gushi.minis.beans.factory.config.ConstructorArgumentValue;

import java.util.*;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 23:14
 */
public class ConstructorArgumentValues {

    private final List<ConstructorArgumentValue> argumentValueList = new ArrayList<>();

    public ConstructorArgumentValues() {}

    public void addArgumentValue(ConstructorArgumentValue argumentValue) {
        this.argumentValueList.add(argumentValue);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        return  this.argumentValueList.get(index);
    }

    public int getArgumentCount() {
        return this.argumentValueList.size();
    }

    public boolean isEmpty() {
        return this.argumentValueList.isEmpty();
    }
}

