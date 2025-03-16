package com.gushi.minis.context;

import java.util.EventObject;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/7 9:46
 */
public class ApplicationEvent extends EventObject {


    protected String msg = null;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.msg = source.toString();
    }
}
