package com.mzying2001.linq.interfaces;

public interface IFunc<ParamType, ReturnType> {
    ReturnType func(ParamType t);
}
