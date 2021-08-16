package com.mzying2001.linq;

import com.mzying2001.linq.interfaces.*;

import java.util.*;

public class Linq<T> implements Iterable<T> {
    private List<T> _list;

    public Linq(Iterable<T> iterable) {
        this._list = Linq.toList(iterable);
    }

    public Linq(T[] arr) {
        this._list = Linq.toList(arr);
    }

    public static <T> Linq<T> from(Iterable<T> iterable) {
        return new Linq<>(iterable);
    }

    public static <T> Linq<T> from(T[] arr) {
        return new Linq<>(arr);
    }

    @Override
    public Iterator<T> iterator() {
        return this._list.iterator();
    }

    @Override
    public String toString() {
        return this._list.toString();
    }

    public boolean all(IFunc<T, Boolean> iFunc) {
        for (var item : this) {
            if (!iFunc.func(item))
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
        for (var item : this) {
            if (iFunc.func(item))
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
        return this._list.contains(value);
    }

    public static <T> boolean contains(Iterable<T> iterable, T value) {
        return new Linq<T>(iterable).contains(value);
    }

    public static <T> boolean contains(T[] arr, T value) {
        return new Linq<T>(arr).contains(value);
    }

    public int count() {
        return this._list.size();
    }

    public static <T> int count(Iterable<T> iterable) {
        return new Linq<T>(iterable).count();
    }

    public int count(IFunc<T, Boolean> iFunc) {
        int ret = 0;
        for (var item : this) {
            if (iFunc.func(item))
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
        List<T> list = new ArrayList<>(this._list.size());
        list.addAll(this._list);
        return list;
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (var item : iterable) {
            list.add(item);
        }
        return list;
    }

    public static <T> List<T> toList(T[] arr) {
        return Arrays.asList(arr);
    }

    public Linq<T> reverse() {
        Collections.reverse(this._list);
        return this;
    }

    public Linq<T> where(IFunc<T, Boolean> iFunc) {
        List<T> list = new ArrayList<>(this.count());
        for (var item : this) {
            if (iFunc.func(item))
                list.add(item);
        }
        this._list = list;
        return this;
    }

    public <ComparableType extends Comparable<ComparableType>> Linq<T> orderBy(IFunc<T, ComparableType> iFunc) {
        Sort.quickSort(this._list, iFunc);
        return this;
    }

    public <ComparableType extends Comparable<ComparableType>>
    Linq<T> orderByDescending(IFunc<T, ComparableType> iFunc) {
        Sort.quickSort(this._list, iFunc);
        Collections.reverse(this._list);
        return this;
    }

    public Iterable<T> select() {
        return this.toList();
    }

    public <ReturnType> Iterable<ReturnType> select(IFunc<T, ReturnType> iFunc) {
        List<ReturnType> list = new ArrayList<>();
        for (var item : this) {
            list.add(iFunc.func(item));
        }
        return list;
    }
}
