package com.mzying2001.linq.interfaces;

public interface IAggregateFunc<T> {
    T func(T result, T current);
}
