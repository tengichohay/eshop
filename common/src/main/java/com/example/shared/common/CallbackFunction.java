package com.example.shared.common;

@FunctionalInterface
public interface CallbackFunction<T> {
    T execute();
}
