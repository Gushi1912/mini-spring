package com.gushi.minis.context;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 14:48
 */
public class ContextRefreshEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextRefreshEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "ContextRefreshEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
