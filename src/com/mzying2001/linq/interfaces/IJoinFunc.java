package com.mzying2001.linq.interfaces;

public interface IJoinFunc<OuterType, InnerType, ResultType> {
    ResultType func(OuterType outer, InnerType inner);
}
