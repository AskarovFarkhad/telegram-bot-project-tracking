package com.askarov.bot.telegram.cache.impl;

import com.askarov.bot.telegram.cache.EmployeeDataCache;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EmployeeDataCacheImpl<R, T> implements EmployeeDataCache<R, T> {

    private final HashMap<R, T> controllerMap = new HashMap<>();

    @Override
    public void addIfAbsent(R key, T value) {
        controllerMap.putIfAbsent(key, value);
    }

    @Override
    public void updateIfPresent(R key, T value) {
        controllerMap.computeIfPresent(key, (k, v) -> value);
    }

    @Override
    public T get(R key) {
        return controllerMap.get(key);
    }

    @Override
    public void remove(R key) {
        controllerMap.remove(key);
    }
}
