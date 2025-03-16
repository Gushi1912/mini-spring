package com.gushi.minis.core.env;

/**
 * @Author Gushiyang
 * @Version 1.0.0
 * @Time 2025/3/14 13:27
 */
public interface Environment extends PropertyResolver{

    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    boolean acceptsProfiles(String...profiles);
}
