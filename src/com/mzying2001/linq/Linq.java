package com.mzying2001.linq;

import com.mzying2001.linq.interfaces.AggregateFunc;
import com.mzying2001.linq.interfaces.IEqualityCompare;
import com.mzying2001.linq.interfaces.IFunc;

import java.util.*;

public class Linq<T> implements Iterable<T> {
    private List<T> _list;

    private Linq() {
    }

    public Linq(T[] arr) {
        this._list = Linq.toList(arr);
    }

    public Linq(Iterable<T> iterable) {
        this._list = Linq.toList(iterable);
    }

    public Linq(Collection<T> collection) {
        this._list = new ArrayList<>(collection.size());
        this._list.addAll(collection);
    }

    public static <T> Linq<T> from(T[] arr) {
        return new Linq<>(arr);
    }

    public static <T> Linq<T> from(Iterable<T> iterable) {
        return new Linq<>(iterable);
    }

    public static <T> Linq<T> from(Collection<T> collection) {
        return new Linq<>(collection);
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

    public double average(IFunc<T, Double> iFunc) {
        return Linq.average(this._list, iFunc);
    }

    public static <T> double average(Iterable<T> iterable, IFunc<T, Double> iFunc) {
        double sum = 0d;
        int cnt = 0;
        for (var item : iterable) {
            sum += iFunc.func(item);
            cnt++;
        }
        return sum / cnt;
    }

    public static <T> double average(Collection<T> collection, IFunc<T, Double> iFunc) {
        return Linq.sum(collection, iFunc) / collection.size();
    }

    public static <T> double average(T[] arr, IFunc<T, Double> iFunc) {
        return Linq.average(Arrays.asList(arr), iFunc);
    }

    public <ComparableType extends Comparable<ComparableType>>
    ComparableType max(IFunc<T, ComparableType> iFunc) {
        return Linq.max(this, iFunc);
    }

    public static <ComparableType extends Comparable<ComparableType>>
    ComparableType max(Iterable<ComparableType> iterable) {
        Iterator<ComparableType> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        ComparableType ans = iterator.next();
        while (iterator.hasNext()) {
            ComparableType next = iterator.next();
            if (ans.compareTo(next) < 0) {
                ans = next;
            }
        }
        return ans;
    }

    public static <ComparableType extends Comparable<ComparableType>>
    ComparableType max(ComparableType[] arr) {
        return Linq.max(Arrays.asList(arr));
    }

    public static <SourceType, ComparableType extends Comparable<ComparableType>>
    ComparableType max(Iterable<SourceType> iterable, IFunc<SourceType, ComparableType> iFunc) {
        Iterator<SourceType> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        ComparableType ans = iFunc.func(iterator.next());
        while (iterator.hasNext()) {
            ComparableType next = iFunc.func(iterator.next());
            if (ans.compareTo(next) < 0) {
                ans = next;
            }
        }
        return ans;
    }

    public static <SourceType, ComparableType extends Comparable<ComparableType>>
    ComparableType max(SourceType[] arr, IFunc<SourceType, ComparableType> iFunc) {
        return Linq.max(Arrays.asList(arr), iFunc);
    }

    public <ComparableType extends Comparable<ComparableType>>
    ComparableType min(IFunc<T, ComparableType> iFunc) {
        return Linq.min(this, iFunc);
    }

    public static <ComparableType extends Comparable<ComparableType>>
    ComparableType min(Iterable<ComparableType> iterable) {
        Iterator<ComparableType> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        ComparableType ans = iterator.next();
        while (iterator.hasNext()) {
            ComparableType next = iterator.next();
            if (ans.compareTo(next) > 0) {
                ans = next;
            }
        }
        return ans;
    }

    public static <ComparableType extends Comparable<ComparableType>>
    ComparableType min(ComparableType[] arr) {
        return Linq.min(Arrays.asList(arr));
    }

    public static <SourceType, ComparableType extends Comparable<ComparableType>>
    ComparableType min(Iterable<SourceType> iterable, IFunc<SourceType, ComparableType> iFunc) {
        Iterator<SourceType> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        ComparableType ans = iFunc.func(iterator.next());
        while (iterator.hasNext()) {
            ComparableType next = iFunc.func(iterator.next());
            if (ans.compareTo(next) > 0) {
                ans = next;
            }
        }
        return ans;
    }

    public double sum(IFunc<T, Double> iFunc) {
        return Linq.sum(this, iFunc);
    }

    public static <T> double sum(Iterable<T> iterable, IFunc<T, Double> iFunc) {
        double ans = 0d;
        for (var item : iterable) {
            ans += iFunc.func(item);
        }
        return ans;
    }

    public static <T> double sum(T[] arr, IFunc<T, Double> iFunc) {
        return Linq.sum(Arrays.asList(arr), iFunc);
    }

    public static <SourceType, ComparableType extends Comparable<ComparableType>>
    ComparableType min(SourceType[] arr, IFunc<SourceType, ComparableType> iFunc) {
        return Linq.min(Arrays.asList(arr), iFunc);
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

    public static <T> List<T> toList(Collection<T> collection) {
        List<T> list = new ArrayList<>(collection.size());
        list.addAll(collection);
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
        return Linq.aggregate(this, aggregateFunc);
    }

    public static <T> T aggregate(Iterable<T> iterable, AggregateFunc<T> aggregateFunc) {
        Iterator<T> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        T result = iterator.next();
        while (iterator.hasNext()) {
            result = aggregateFunc.func(result, iterator.next());
        }
        return result;
    }

    public static <T> T aggregate(T[] arr, AggregateFunc<T> aggregateFunc) {
        return Linq.aggregate(Arrays.asList(arr), aggregateFunc);
    }

    public Linq<T> concat(Iterable<T> iterable) {
        for (var item : iterable) {
            this._list.add(item);
        }
        return this;
    }

    public Linq<T> concat(T[] arr) {
        return this.concat(Arrays.asList(arr));
    }

    public static <T> List<T> concat(Iterable<T> iterable1, Iterable<T> iterable2) {
        List<T> list = Linq.toList(iterable1);
        for (var item : iterable2) {
            list.add(item);
        }
        return list;
    }

    public static <T> List<T> concat(Collection<T> collection1, Collection<T> collection2) {
        List<T> ret = new ArrayList<>(collection1.size() + collection2.size());
        ret.addAll(collection1);
        ret.addAll(collection2);
        return ret;
    }

    public static <T> List<T> concat(Iterable<T> iterable, T[] arr) {
        return Linq.concat(iterable, Arrays.asList(arr));
    }

    public static <T> List<T> concat(T[] arr, Iterable<T> iterable) {
        return Linq.concat(Arrays.asList(arr), iterable);
    }

    public static <T> List<T> concat(T[] arr1, T[] arr2) {
        return Linq.concat(Arrays.asList(arr1), Arrays.asList(arr2));
    }

    public Linq<T> distinct() {
        Set<T> set = new HashSet<>(this.count());
        set.addAll(this._list);
        this._list.clear();
        this._list.addAll(set);
        return this;
    }

    public static <T> List<T> distinct(Iterable<T> iterable) {
        Set<T> set = new HashSet<>();
        for (var item : iterable) {
            set.add(item);
        }
        return Linq.toList(set);
    }

    public static <T> List<T> distinct(Collection<T> collection) {
        Set<T> set = new HashSet<>(collection.size());
        set.addAll(collection);
        List<T> ret = new ArrayList<>(set.size());
        ret.addAll(set);
        return ret;
    }

    public static <T> List<T> distinct(T[] arr) {
        return Linq.distinct(Arrays.asList(arr));
    }

    public Linq<T> distinct(IEqualityCompare<T> equalityCompare) {
        this._list = Linq.distinct(this._list, equalityCompare);
        return this;
    }

    public static <T> List<T> distinct(Iterable<T> iterable, IEqualityCompare<T> equalityCompare) {
        return Linq.distinct(Linq.toList(iterable), equalityCompare);
    }

    public static <T> List<T> distinct(List<T> list, IEqualityCompare<T> equalityCompare) {
        Set<Integer> skipIndexes = new HashSet<>(list.size());
        List<T> newList = new ArrayList<>(list.size());
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            if (skipIndexes.contains(i)) {
                continue;
            }
            T item = list.get(i);
            for (int j = i + 1; j < listSize; j++) {
                if (equalityCompare.equals(item, list.get(j))) {
                    skipIndexes.add(j);
                }
            }
            newList.add(item);
        }
        return newList;
    }

    public static <T> List<T> distinct(T[] arr, IEqualityCompare<T> equalityCompare) {
        return Linq.distinct(Arrays.asList(arr), equalityCompare);
    }

    public Linq<T> except(Iterable<T> iterable) {
        this._list = Linq.except(this._list, iterable);
        return this;
    }

    public Linq<T> except(T[] arr) {
        this._list = Linq.except(this._list, Arrays.asList(arr));
        return this;
    }

    public static <T> List<T> except(Iterable<T> iterable, Iterable<T> ex) {
        return Linq.except(Linq.toList(iterable), ex);
    }

    public static <T> List<T> except(Collection<T> collection, Iterable<T> ex) {
        Set<T> set = new HashSet<>();
        for (var item : ex) {
            set.add(item);
        }
        List<T> newList = new ArrayList<>(collection.size());
        for (var item : collection) {
            if (!set.contains(item)) {
                newList.add(item);
            }
        }
        return newList;
    }

    public static <T> List<T> except(T[] arr, Iterable<T> ex) {
        return Linq.except(Arrays.asList(arr), ex);
    }

    public static <T> List<T> except(Iterable<T> iterable, T[] ex) {
        return Linq.except(iterable, Arrays.asList(ex));
    }

    public static <T> List<T> except(T[] arr, T[] ex) {
        return Linq.except(Arrays.asList(arr), Arrays.asList(ex));
    }

    public Linq<T> except(Iterable<T> iterable, IEqualityCompare<T> equalityCompare) {
        this._list = Linq.except(this._list, iterable, equalityCompare);
        return this;
    }

    public static <T> List<T> except(Iterable<T> iterable, Iterable<T> ex, IEqualityCompare<T> equalityCompare) {
        return Linq.except(Linq.toList(iterable), ex, equalityCompare);
    }

    public static <T> List<T> except(Collection<T> collection, Iterable<T> ex, IEqualityCompare<T> equalityCompare) {
        List<T> newList = new ArrayList<>(collection.size());
        for (var item : collection) {
            boolean flag = true;
            for (var exItem : ex) {
                if (equalityCompare.equals(item, exItem)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                newList.add(item);
            }
        }
        return newList;
    }

    public static <T> List<T> except(T[] arr, Iterable<T> ex, IEqualityCompare<T> equalityCompare) {
        return Linq.except(Arrays.asList(arr), ex, equalityCompare);
    }

    public static <T> List<T> except(Iterable<T> iterable, T[] ex, IEqualityCompare<T> equalityCompare) {
        return Linq.except(iterable, Arrays.asList(ex), equalityCompare);
    }

    public static <T> List<T> except(T[] arr, T[] ex, IEqualityCompare<T> equalityCompare) {
        return Linq.except(Arrays.asList(arr), Arrays.asList(ex), equalityCompare);
    }

    public Linq<T> reverse() {
        Collections.reverse(this._list);
        return this;
    }

    public static <T> List<T> reverse(Iterable<T> iterable) {
        Stack<T> stack = new Stack<>();
        for (var item : iterable) {
            stack.push(item);
        }
        List<T> list = new ArrayList<>(stack.size());
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public static <T> List<T> reverse(Collection<T> collection) {
        List<T> ret = new ArrayList<>(collection.size());
        ret.addAll(collection);
        Collections.reverse(ret);
        return ret;
    }

    public static <T> List<T> reverse(T[] arr) {
        List<T> list = new ArrayList<>(arr.length);
        list.addAll(Arrays.asList(arr));
        Collections.reverse(list);
        return list;
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

    public Linq<T> union(T[] arr) {
        return this.union(Arrays.asList(arr));
    }

    public static <T> List<T> union(Iterable<T> iterable1, Iterable<T> iterable2) {
        Set<T> set = new HashSet<>();
        for (var item : iterable1) {
            set.add(item);
        }
        for (var item : iterable2) {
            set.add(item);
        }
        List<T> list = new ArrayList<>(set.size());
        list.addAll(set);
        return list;
    }

    public static <T> List<T> union(T[] arr, Iterable<T> iterable) {
        return Linq.union(Arrays.asList(arr), iterable);
    }

    public static <T> List<T> union(Iterable<T> iterable, T[] arr) {
        return Linq.union(iterable, Arrays.asList(arr));
    }

    public static <T> List<T> union(T[] arr1, T[] arr2) {
        return Linq.union(Arrays.asList(arr1), Arrays.asList(arr2));
    }

    public Linq<T> union(Iterable<T> iterable, IEqualityCompare<T> equalityCompare) {
        return this.concat(iterable).distinct(equalityCompare);
    }

    public Linq<T> union(T[] arr, IEqualityCompare<T> equalityCompare) {
        return this.concat(arr).distinct(equalityCompare);
    }

    public static <T> List<T> union(Iterable<T> iterable1, Iterable<T> iterable2, IEqualityCompare<T> equalityCompare) {
        List<T> list = Linq.concat(iterable1, iterable2);
        return Linq.distinct(list, equalityCompare);
    }

    public static <T> List<T> union(T[] arr, Iterable<T> iterable, IEqualityCompare<T> equalityCompare) {
        return Linq.union(Arrays.asList(arr), iterable, equalityCompare);
    }

    public static <T> List<T> union(Iterable<T> iterable, T[] arr, IEqualityCompare<T> equalityCompare) {
        return Linq.union(iterable, Arrays.asList(arr), equalityCompare);
    }

    public static <T> List<T> union(T[] arr1, T[] arr2, IEqualityCompare<T> equalityCompare) {
        return Linq.union(Arrays.asList(arr1), Arrays.asList(arr2), equalityCompare);
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

    public List<T> select() {
        return this.toList();
    }

    public <ReturnType> List<ReturnType> select(IFunc<T, ReturnType> iFunc) {
        return Linq.select(this._list, iFunc);
    }

    public static <SourceType, ReturnType>
    List<ReturnType> select(Iterable<SourceType> iterable, IFunc<SourceType, ReturnType> iFunc) {
        List<ReturnType> list = new ArrayList<>();
        for (var item : iterable) {
            list.add(iFunc.func(item));
        }
        return list;
    }

    public static <SourceType, ReturnType>
    List<ReturnType> select(Collection<SourceType> collection, IFunc<SourceType, ReturnType> iFunc) {
        List<ReturnType> ret = new ArrayList<>(collection.size());
        for (var item : collection) {
            ret.add(iFunc.func(item));
        }
        return ret;
    }

    public static <SourceType, ReturnType>
    List<ReturnType> select(SourceType[] arr, IFunc<SourceType, ReturnType> iFunc) {
        return Linq.select(Arrays.asList(arr), iFunc);
    }
}
