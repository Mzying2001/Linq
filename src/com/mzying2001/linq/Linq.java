package com.mzying2001.linq;

import com.mzying2001.linq.interfaces.*;

import java.util.*;

public class Linq<T> implements Iterable<T> {
    private final Iterable<T> _iterable;

    public Linq(Iterable<T> iterable) {
        this._iterable = iterable;
    }

    public Linq(T[] arr) {
        this._iterable = Arrays.asList(arr);
    }

    public static <T> Linq<T> from(Iterable<T> iterable) {
        return new Linq<T>(iterable);
    }

    public static <T> Linq<T> from(T[] arr) {
        return new Linq<T>(arr);
    }

    @Override
    public Iterator<T> iterator() {
        return _iterable.iterator();
    }

    @Override
    public String toString() {
        return _iterable.toString();
    }

    public boolean all(IFunc<T, Boolean> iFunc) {
        for (T t : _iterable) {
            if (!iFunc.func(t))
                return false;
        }
        return true;
    }

    public static <T> boolean all(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        return new Linq<T>(iterable).all(iFunc);
    }

    public static <T> boolean all(T[] arr, IFunc<T, Boolean> iFunc) {
        return new Linq<T>(arr).all(iFunc);
    }

    public boolean any(IFunc<T, Boolean> iFunc) {
        for (T t : _iterable) {
            if (iFunc.func(t))
                return true;
        }
        return false;
    }

    public static <T> boolean any(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        return new Linq<T>(iterable).any(iFunc);
    }

    public static <T> boolean any(T[] arr, IFunc<T, Boolean> iFunc) {
        return new Linq<T>(arr).any(iFunc);
    }

    public boolean contains(T value) {
        for (T t : _iterable) {
            if (t.equals(value))
                return true;
        }
        return false;
    }

    public static <T> boolean contains(Iterable<T> iterable, T value) {
        return new Linq<T>(iterable).contains(value);
    }

    public static <T> boolean contains(T[] arr, T value) {
        return new Linq<T>(arr).contains(value);
    }

    public int count() {
        int ret = 0;
        for (T t : _iterable) {
            ret++;
        }
        return ret;
    }

    public static <T> int count(Iterable<T> iterable) {
        return new Linq<T>(iterable).count();
    }

    public int count(IFunc<T, Boolean> iFunc) {
        int ret = 0;
        for (T t : _iterable) {
            if (iFunc.func(t))
                ret++;
        }
        return ret;
    }

    public static <T> int count(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        return new Linq<T>(iterable).count(iFunc);
    }

    public static <T> int count(T[] arr, IFunc<T, Boolean> iFunc) {
        return new Linq<T>(arr).count(iFunc);
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (T t : _iterable) {
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        return new Linq<T>(iterable).toList();
    }

    public static <T> List<T> toList(T[] arr) {
        return new Linq<T>(arr).toList();
    }

    public Linq<T> where(IFunc<T, Boolean> iFunc) {
        List<T> list = new ArrayList<>();
        for (T t : _iterable) {
            if (iFunc.func(t))
                list.add(t);
        }
        return new Linq<T>(list);
    }

    public Iterable<T> select() {
        return _iterable;
    }

    public <ReturnType> Iterable<ReturnType> select(IFunc<T, ReturnType> iFunc) {
        List<ReturnType> list = new ArrayList<>();
        for (T t : _iterable) {
            list.add(iFunc.func(t));
        }
        return list;
    }
}
