package com.askarov.bot.telegram.cache;

public interface EmployeeDataCache<R, T>  {

    void addIfAbsent(R key, T value);

    void updateIfPresent(R key, T value);

    T get(R key);

    void remove(R key);
}
