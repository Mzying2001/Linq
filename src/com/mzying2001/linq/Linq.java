package com.mzying2001.linq;

import com.mzying2001.linq.interfaces.*;

import java.util.*;

public class Linq<T> implements Iterable<T> {
    private List<T> _list;

    private Linq() {
    }

    public Linq(Iterable<T> iterable) {
        this._list = Linq.toList(iterable);
    }

    public Linq(List<T> list) {
        this._list = new ArrayList<>(list.size());
        this._list.addAll(list);
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

    public static Linq<Boolean> from(boolean[] arr) {
        Linq<Boolean> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
    }

    public static Linq<Byte> from(byte[] arr) {
        Linq<Byte> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
    }

    public static Linq<Character> from(char[] arr) {
        Linq<Character> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
    }

    public static Linq<Short> from(short[] arr) {
        Linq<Short> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
    }

    public static Linq<Integer> from(int[] arr) {
        Linq<Integer> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
    }

    public static Linq<Long> from(long[] arr) {
        Linq<Long> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
    }

    public static Linq<Float> from(float[] arr) {
        Linq<Float> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
    }

    public static Linq<Double> from(double[] arr) {
        Linq<Double> linq = new Linq<>();
        linq._list = Linq.toList(arr);
        return linq;
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
        return Linq.all(this, iFunc);
    }

    public static <T> boolean all(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        for (var item : iterable) {
            if (!iFunc.func(item))
                return false;
        }
        return true;
    }

    public static <T> boolean all(T[] arr, IFunc<T, Boolean> iFunc) {
        for (var item : arr) {
            if (!iFunc.func(item))
                return false;
        }
        return true;
    }

    public boolean any(IFunc<T, Boolean> iFunc) {
        return Linq.any(this, iFunc);
    }

    public static <T> boolean any(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        for (var item : iterable) {
            if (iFunc.func(item))
                return true;
        }
        return false;
    }

    public static <T> boolean any(T[] arr, IFunc<T, Boolean> iFunc) {
        for (var item : arr) {
            if (iFunc.func(item))
                return true;
        }
        return false;
    }

    public boolean contains(T value) {
        return this._list.contains(value);
    }

    public static <T> boolean contains(Iterable<T> iterable, T value) {
        if (value == null) {
            for (var item : iterable) {
                if (item == null)
                    return true;
            }
        } else {
            for (var item : iterable) {
                if (value.equals(item))
                    return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T[] arr, T value) {
        return Arrays.asList(arr).contains(value);
    }

    public int count() {
        return this._list.size();
    }

    public static <T> int count(Iterable<T> iterable) {
        int ret = 0;
        for (var ignored : iterable) {
            ret++;
        }
        return ret;
    }

    public int count(IFunc<T, Boolean> iFunc) {
        return Linq.count(this, iFunc);
    }

    public static <T> int count(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        int ret = 0;
        for (var item : iterable) {
            if (iFunc.func(item))
                ret++;
        }
        return ret;
    }

    public static <T> int count(T[] arr, IFunc<T, Boolean> iFunc) {
        int ret = 0;
        for (var item : arr) {
            if (iFunc.func(item))
                ret++;
        }
        return ret;
    }

    public boolean empty() {
        return this.count() == 0;
    }

    public T first() {
        if (this.count() == 0) {
            return null;
        } else {
            return this._list.get(0);
        }
    }

    public T first(IFunc<T, Boolean> iFunc) {
        for (var item : this) {
            if (iFunc.func(item))
                return item;
        }
        return null;
    }

    public T last() {
        int index = this.count() - 1;
        if (index == -1) {
            return null;
        } else {
            return this._list.get(index);
        }
    }

    public T last(IFunc<T, Boolean> iFunc) {
        List<T> list = this._list;
        if (list.size() == 0) {
            return null;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            T item = list.get(i);
            if (iFunc.func(item))
                return item;
        }
        return null;
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
        List<T> list = new ArrayList<>(arr.length);
        Collections.addAll(list, arr);
        return list;
    }

    public static List<Boolean> toList(boolean[] arr) {
        List<Boolean> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public static List<Byte> toList(byte[] arr) {
        List<Byte> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public static List<Character> toList(char[] arr) {
        List<Character> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public static List<Short> toList(short[] arr) {
        List<Short> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public static List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public static List<Long> toList(long[] arr) {
        List<Long> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public static List<Float> toList(float[] arr) {
        List<Float> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public static List<Double> toList(double[] arr) {
        List<Double> list = new ArrayList<>(arr.length);
        for (var item : arr) {
            list.add(item);
        }
        return list;
    }

    public T aggregate(AggregateFunc<T> aggregateFunc) {
        Iterator<T> iterator = this.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        T result = iterator.next();
        while (iterator.hasNext()) {
            result = aggregateFunc.func(result, iterator.next());
        }
        return result;
    }

    public Linq<T> concat(Iterable<T> iterable) {
        for (var item : iterable) {
            this._list.add(item);
        }
        return this;
    }

    public Linq<T> distinct() {
        Set<T> set = new HashSet<>(this.count());
        for (var item : this) {
            set.add(item);
        }
        this._list.clear();
        this._list.addAll(set);
        return this;
    }

    public Linq<T> distinct(IEqualityCompare<T> equalityCompare) {
        List<T> oldList = this._list;
        List<T> newList = new ArrayList<>(oldList.size());
        for (int i = 0; i < oldList.size(); i++) {
            T item = oldList.get(i);
            for (int j = i + 1; j < oldList.size(); j++) {
                if (equalityCompare.equals(item, oldList.get(j)))
                    oldList.remove(j--);
            }
            newList.add(item);
        }
        this._list = newList;
        return this;
    }

    public Linq<T> except(Iterable<T> iterable) {
        Set<T> set = new HashSet<>();
        for (var item : iterable) {
            set.add(item);
        }
        List<T> list = this._list;
        for (int i = 0; i < list.size(); i++) {
            if (set.contains(list.get(i)))
                list.remove(i--);
        }
        return this;
    }

    public Linq<T> except(Iterable<T> iterable, IEqualityCompare<T> equalityCompare) {
        List<T> newList = new ArrayList<>(this.count());
        for (var item : this) {
            boolean flag = true;
            for (var ex : iterable) {
                if (equalityCompare.equals(item, ex)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                newList.add(item);
            }
        }
        this._list = newList;
        return this;
    }

    public Linq<T> reverse() {
        Collections.reverse(this._list);
        return this;
    }

    public Linq<T> union(Iterable<T> iterable) {
        Set<T> set = new HashSet<>();
        for (var item : this) {
            set.add(item);
        }
        for (var item : iterable) {
            set.add(item);
        }
        this._list.clear();
        this._list.addAll(set);
        return this;
    }

    public Linq<T> union(Iterable<T> iterable, IEqualityCompare<T> equalityCompare) {
        return this.concat(iterable).distinct(equalityCompare);
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

    public <ComparableType extends Comparable<ComparableType>>
    Linq<T> orderBy(IFunc<T, ComparableType> iFunc) {
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
