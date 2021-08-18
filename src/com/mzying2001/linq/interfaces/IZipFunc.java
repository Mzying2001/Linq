package com.mzying2001.linq.interfaces;

public interface IZipFunc<SourceType, ResultType> {
    ResultType zip(SourceType a, SourceType b);
}
