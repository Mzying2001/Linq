package com.mzying2001.linq.interfaces;

public interface AggregateFunc<T> {
    T func(T result, T current);
}
