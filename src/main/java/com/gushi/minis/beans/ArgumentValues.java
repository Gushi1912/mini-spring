package com.gushi.minis.beans;

import java.util.*;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 23:14
 */
public class ArgumentValues {

    private final List<ArgumentValue> argumentValueList = new ArrayList<>();

    public ArgumentValues() {}

    public void addArgumentValue(ArgumentValue argumentValue) {
        this.argumentValueList.add(argumentValue);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        return  this.argumentValueList.get(index);
    }

    public int getArgumentCount() {
        return this.argumentValueList.size();
    }

    public boolean isEmpty() {
        return this.argumentValueList.isEmpty();
    }
}

