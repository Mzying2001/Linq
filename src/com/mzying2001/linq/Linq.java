package com.mzying2001.linq;

import com.mzying2001.linq.interfaces.*;

import java.util.*;

public class Linq<T> implements Iterable<T> {
    protected List<T> _list;

    protected Linq() {
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

    @Override
    public Iterator<T> iterator() {
        return this._list.iterator();
    }

    @Override
    public String toString() {
        return this._list.toString();
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

    public Linq<T> append(T value) {
        this._list.add(value);
        return this;
    }

    public static <T> List<T> append(Iterable<T> iterable, T value) {
        List<T> list = Linq.toList(iterable);
        list.add(value);
        return list;
    }

    public static <T> List<T> append(Collection<T> collection, T value) {
        List<T> list = new ArrayList<>(collection.size() + 1);
        list.addAll(collection);
        list.add(value);
        return list;
    }

    public static <T> List<T> append(T[] arr, T value) {
        return Linq.append(Arrays.asList(arr), value);
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

    public Linq<T> defaultIfEmpty(T defaultValue) {
        if (this.empty()) {
            this._list.add(defaultValue);
        }
        return this;
    }

    public static <T> List<T> defaultIfEmpty(Iterable<T> iterable, T defaultValue) {
        List<T> list;
        if (Linq.empty(iterable)) {
            list = Linq.toList(iterable);
        } else {
            list = new ArrayList<>();
            list.add(defaultValue);
        }
        return list;
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

    public Linq<T> distinct(IEqualityComparator<T> equalityComparator) {
        this._list = Linq.distinct(this._list, equalityComparator);
        return this;
    }

    public static <T> List<T> distinct(Iterable<T> iterable, IEqualityComparator<T> equalityComparator) {
        return Linq.distinct(Linq.toList(iterable), equalityComparator);
    }

    public static <T> List<T> distinct(List<T> list, IEqualityComparator<T> equalityComparator) {
        Set<Integer> skipIndexes = new HashSet<>(list.size());
        List<T> newList = new ArrayList<>(list.size());
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            if (skipIndexes.contains(i)) {
                continue;
            }
            T item = list.get(i);
            for (int j = i + 1; j < listSize; j++) {
                if (equalityComparator.equals(item, list.get(j))) {
                    skipIndexes.add(j);
                }
            }
            newList.add(item);
        }
        return newList;
    }

    public static <T> List<T> distinct(T[] arr, IEqualityComparator<T> equalityComparator) {
        return Linq.distinct(Arrays.asList(arr), equalityComparator);
    }

    public T elementAt(int index) {
        if (index < 0) {
            return null;
        } else {
            return index < this.count() ? this._list.get(index) : null;
        }
    }

    public static <T> T elementAt(Iterable<T> iterable, int index) {
        if (index < 0) {
            return null;
        }
        Iterator<T> iterator = iterable.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            if (i == index) {
                return iterator.next();
            } else {
                iterator.next();
            }
        }
        return null;
    }

    public T elementAtOrDefault(int index, T defaultValue) {
        if (index < 0) {
            return defaultValue;
        } else {
            return index < this.count() ? this._list.get(index) : defaultValue;
        }
    }

    public static <T> T elementAtOrDefault(Iterable<T> iterable, int index, T defaultValue) {
        if (index < 0) {
            return defaultValue;
        }
        Iterator<T> iterator = iterable.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            if (i == index) {
                return iterator.next();
            } else {
                iterator.next();
            }
        }
        return defaultValue;
    }

    public boolean empty() {
        return this._list.isEmpty();
    }

    public static <T> boolean empty(Iterable<T> iterable) {
        return !iterable.iterator().hasNext();
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

    public Linq<T> except(Iterable<T> iterable, IEqualityComparator<T> equalityComparator) {
        this._list = Linq.except(this._list, iterable, equalityComparator);
        return this;
    }

    public static <T> List<T> except(Iterable<T> iterable, Iterable<T> ex, IEqualityComparator<T> equalityComparator) {
        return Linq.except(Linq.toList(iterable), ex, equalityComparator);
    }

    public static <T> List<T> except(Collection<T> collection, Iterable<T> ex, IEqualityComparator<T> equalityComparator) {
        List<T> newList = new ArrayList<>(collection.size());
        for (var item : collection) {
            boolean flag = true;
            for (var exItem : ex) {
                if (equalityComparator.equals(item, exItem)) {
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

    public static <T> List<T> except(T[] arr, Iterable<T> ex, IEqualityComparator<T> equalityComparator) {
        return Linq.except(Arrays.asList(arr), ex, equalityComparator);
    }

    public static <T> List<T> except(Iterable<T> iterable, T[] ex, IEqualityComparator<T> equalityComparator) {
        return Linq.except(iterable, Arrays.asList(ex), equalityComparator);
    }

    public static <T> List<T> except(T[] arr, T[] ex, IEqualityComparator<T> equalityComparator) {
        return Linq.except(Arrays.asList(arr), Arrays.asList(ex), equalityComparator);
    }

    public T first() {
        return this.empty() ? null : this._list.get(0);
    }

    public static <T> T first(Iterable<T> iterable) {
        return Linq.empty(iterable) ? null : iterable.iterator().next();
    }

    public T first(IFunc<T, Boolean> iFunc) {
        return Linq.first(this, iFunc);
    }

    public static <T> T first(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        for (var item : iterable) {
            if (iFunc.func(item)) {
                return item;
            }
        }
        return null;
    }

    public T firstOrDefault(T defaultValue) {
        return this.empty() ? defaultValue : this._list.get(0);
    }

    public static <T> T firstOrDefault(Iterable<T> iterable, T defaultValue) {
        return Linq.empty(iterable) ? defaultValue : iterable.iterator().next();
    }

    public T firstOrDefault(IFunc<T, Boolean> iFunc, T defaultValue) {
        return Linq.firstOrDefault(this, iFunc, defaultValue);
    }

    public static <T> T firstOrDefault(Iterable<T> iterable, IFunc<T, Boolean> iFunc, T defaultValue) {
        for (var item : iterable) {
            if (iFunc.func(item)) {
                return item;
            }
        }
        return defaultValue;
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

    public List<T> getList() {
        return this._list;
    }

    public T last() {
        int index = this.count() - 1;
        if (index == -1) {
            return null;
        } else {
            return this._list.get(index);
        }
    }

    public static <T> T last(Iterable<T> iterable) {
        T ret = null;
        for (var item : iterable) {
            ret = item;
        }
        return ret;
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

    public static <T> T last(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        T ret = null;
        for (var item : iterable) {
            if (iFunc.func(item)) {
                ret = item;
            }
        }
        return ret;
    }

    public T lastOrDefault(T defaultValue) {
        int index = this.count() - 1;
        if (index == -1) {
            return defaultValue;
        } else {
            return this._list.get(index);
        }
    }

    public static <T> T lastOrDefault(Iterable<T> iterable, T defaultValue) {
        T ret = defaultValue;
        for (var item : iterable) {
            ret = item;
        }
        return ret;
    }

    public T lastOrDefault(IFunc<T, Boolean> iFunc, T defaultValue) {
        List<T> list = this._list;
        if (list.size() == 0) {
            return defaultValue;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            T item = list.get(i);
            if (iFunc.func(item)) {
                return item;
            }
        }
        return defaultValue;
    }

    public static <T> T lastOrDefault(Iterable<T> iterable, IFunc<T, Boolean> iFunc, T defaultValue) {
        T ret = defaultValue;
        for (var item : iterable) {
            if (iFunc.func(item)) {
                ret = item;
            }
        }
        return ret;
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

    public <ComparableType extends Comparable<ComparableType>>
    OrderedLinq<T> orderBy(IFunc<T, ComparableType> iFunc) {
        Sort.quickSort(this._list, iFunc);
        return OrderedLinq.create(this, iFunc);
    }

    public <ComparableType extends Comparable<ComparableType>>
    OrderedLinq<T> orderByDescending(IFunc<T, ComparableType> iFunc) {
        IComparator<T> comparator = (a, b) -> -iFunc.func(a).compareTo(iFunc.func(b));
        Sort.quickSort(this._list, comparator);
        return OrderedLinq.create(this, comparator);
    }

    public Linq<T> prepend(T value) {
        this._list.add(0, value);
        return this;
    }

    public static <T> List<T> prepend(Iterable<T> iterable, T value) {
        List<T> list = new ArrayList<>();
        list.add(value);
        for (var item : iterable) {
            list.add(item);
        }
        return list;
    }

    public static <T> List<T> prepend(Collection<T> collection, T value) {
        List<T> list = new ArrayList<>(collection.size() + 1);
        list.add(value);
        list.addAll(collection);
        return list;
    }

    public static int[] range(int start, int count) {
        if (count <= 0) {
            return new int[0];
        } else {
            int[] arr = new int[count];
            for (int i = 0; i < count; i++) {
                arr[i] = start + i;
            }
            return arr;
        }
    }

    public Linq<T> repeat(int count) {
        this._list = Linq.repeat(this._list, count);
        return this;
    }

    public static <T> List<T> repeat(Iterable<T> iterable, int count) {
        List<T> list = new ArrayList<>();
        while (count-- > 0) {
            for (var item : iterable) {
                list.add(item);
            }
        }
        return list;
    }

    public static <T> List<T> repeat(Collection<T> collection, int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(collection.size() * count);
        while (count-- > 0) {
            list.addAll(collection);
        }
        return list;
    }

    public static <T> List<T> repeat(T[] arr, int count) {
        return Linq.repeat(Arrays.asList(arr), count);
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

    public Linq<T> select() {
        return this;
    }

    public <ReturnType> Linq<ReturnType> select(IFunc<T, ReturnType> iFunc) {
        Linq<ReturnType> linq = new Linq<>();
        linq._list = Linq.select(this._list, iFunc);
        return linq;
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

    public <ReturnType, IterableType extends Iterable<ReturnType>>
    Linq<ReturnType> selectMany(IFunc<T, IterableType> iFunc) {
        Linq<ReturnType> linq = new Linq<>();
        linq._list = Linq.selectMany(this, iFunc);
        return linq;
    }

    public static <SourceType, ReturnType, IterableType extends Iterable<ReturnType>>
    List<ReturnType> selectMany(Iterable<SourceType> iterable, IFunc<SourceType, IterableType> iFunc) {
        List<ReturnType> list = new ArrayList<>();
        for (var outItem : iterable) {
            for (var inItem : iFunc.func(outItem)) {
                list.add(inItem);
            }
        }
        return list;
    }

    public static <SourceType, ReturnType, IterableType extends Iterable<ReturnType>>
    List<ReturnType> selectMany(SourceType[] arr, IFunc<SourceType, IterableType> iFunc) {
        return Linq.selectMany(Arrays.asList(arr), iFunc);
    }

    public <ReturnType, IterItemType, IterableType extends Iterable<IterItemType>>
    Linq<ReturnType> selectMany(IFunc<T, IterableType> getIterFunc, IFunc<IterItemType, ReturnType> selectFunc) {
        Linq<ReturnType> linq = new Linq<>();
        linq._list = Linq.selectMany(this, getIterFunc, selectFunc);
        return linq;
    }

    public static <SourceType, ReturnType, IterItemType, IterableType extends Iterable<IterItemType>>
    List<ReturnType> selectMany(
            Iterable<SourceType> iterable,
            IFunc<SourceType, IterableType> getIterFunc,
            IFunc<IterItemType, ReturnType> selectFunc
    ) {
        List<ReturnType> list = new ArrayList<>();
        for (var outItem : iterable) {
            for (var inItem : getIterFunc.func(outItem)) {
                list.add(selectFunc.func(inItem));
            }
        }
        return list;
    }

    public static <SourceType, ReturnType, IterItemType, IterableType extends Iterable<IterItemType>>
    List<ReturnType> selectMany(
            SourceType[] arr,
            IFunc<SourceType, IterableType> getIterFunc,
            IFunc<IterItemType, ReturnType> selectFunc
    ) {
        return Linq.selectMany(Arrays.asList(arr), getIterFunc, selectFunc);
    }

    public boolean sequenceEqual(Iterable<T> iterable) {
        return Linq.sequenceEqual(this, iterable);
    }

    public boolean sequenceEqual(T[] arr) {
        return Linq.sequenceEqual(this, Arrays.asList(arr));
    }

    public static <T> boolean sequenceEqual(Iterable<T> iterable1, Iterable<T> iterable2) {
        Iterator<T> iterator1 = iterable1.iterator();
        Iterator<T> iterator2 = iterable2.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }
        return !(iterator1.hasNext() || iterator2.hasNext());
    }

    public static <T> boolean sequenceEqual(Iterable<T> iterable, T[] arr) {
        return Linq.sequenceEqual(iterable, Arrays.asList(arr));
    }

    public static <T> boolean sequenceEqual(T[] arr, Iterable<T> iterable) {
        return Linq.sequenceEqual(Arrays.asList(arr), iterable);
    }

    public static <T> boolean sequenceEqual(T[] arr1, T[] arr2) {
        return Linq.sequenceEqual(Arrays.asList(arr1), Arrays.asList(arr2));
    }

    public boolean sequenceEqual(Iterable<T> iterable, IEqualityComparator<T> equalityComparator) {
        return Linq.sequenceEqual(this, iterable, equalityComparator);
    }

    public boolean sequenceEqual(T[] arr, IEqualityComparator<T> equalityComparator) {
        return this.sequenceEqual(Arrays.asList(arr), equalityComparator);
    }

    public static <T>
    boolean sequenceEqual(Iterable<T> iterable1, Iterable<T> iterable2, IEqualityComparator<T> equalityComparator) {
        Iterator<T> iterator1 = iterable1.iterator();
        Iterator<T> iterator2 = iterable2.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (!equalityComparator.equals(iterator1.next(), iterator2.next())) {
                return false;
            }
        }
        return !(iterator1.hasNext() || iterator2.hasNext());
    }

    public static <T> boolean sequenceEqual(Iterable<T> iterable, T[] arr, IEqualityComparator<T> equalityComparator) {
        return Linq.sequenceEqual(iterable, Arrays.asList(arr), equalityComparator);
    }

    public static <T> boolean sequenceEqual(T[] arr, Iterable<T> iterable, IEqualityComparator<T> equalityComparator) {
        return Linq.sequenceEqual(Arrays.asList(arr), iterable, equalityComparator);
    }

    public static <T> boolean sequenceEqual(T[] arr1, T[] arr2, IEqualityComparator<T> equalityComparator) {
        return Linq.sequenceEqual(Arrays.asList(arr1), Arrays.asList(arr2, equalityComparator));
    }

    public T single() {
        return this.count() == 1 ? this.first() : null;
    }

    public static <T> T single(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            T first = iterator.next();
            return iterator.hasNext() ? null : first;
        } else {
            return null;
        }
    }

    public T single(IFunc<T, Boolean> iFunc) {
        if (this.count() == 1) {
            T first = this.first();
            return iFunc.func(first) ? first : null;
        } else {
            return null;
        }
    }

    public static <T> T single(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            T first = iterator.next();
            if (!iterator.hasNext() && iFunc.func(first)) {
                return first;
            }
        }
        return null;
    }

    public T singleOrDefault(T defaultValue) {
        return this.count() == 1 ? this.first() : defaultValue;
    }

    public static <T> T singleOrDefault(Iterable<T> iterable, T defaultValue) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            T first = iterator.next();
            return iterator.hasNext() ? defaultValue : first;
        } else {
            return defaultValue;
        }
    }

    public T singleOrDefault(IFunc<T, Boolean> iFunc, T defaultValue) {
        if (this.count() == 1) {
            T first = this.first();
            return iFunc.func(first) ? first : defaultValue;
        } else {
            return defaultValue;
        }
    }

    public static <T> T singleOrDefault(Iterable<T> iterable, IFunc<T, Boolean> iFunc, T defaultValue) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            T first = iterator.next();
            if (!iterator.hasNext() && iFunc.func(first)) {
                return first;
            }
        }
        return defaultValue;
    }

    public Linq<T> skip(int count) {
        this._list = Linq.skip(this._list, count);
        return this;
    }

    public static <T> List<T> skip(Iterable<T> iterable, int count) {
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext() && count-- > 0) {
            iterator.next();
        }
        List<T> ret = new ArrayList<>();
        while (iterator.hasNext()) {
            ret.add(iterator.next());
        }
        return ret;
    }

    public static <T> List<T> skip(List<T> list, int count) {
        int size = list.size();
        List<T> newList = new ArrayList<>(size);
        if (count < size) {
            for (int i = count; i < size; i++) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    public static <T> List<T> skip(T[] arr, int count) {
        return Linq.skip(Arrays.asList(arr), count);
    }

    public Linq<T> skipLast(int count) {
        while (!this.empty() && count-- > 0) {
            this._list.remove(this.count() - 1);
        }
        return this;
    }

    public static <T> List<T> skipLast(Iterable<T> iterable, int count) {
        List<T> list = Linq.toList(iterable);
        while (!list.isEmpty() && count-- > 0) {
            list.remove(list.size() - 1);
        }
        return list;
    }

    public static <T> List<T> skipLast(T[] arr, int count) {
        return Linq.skipLast(Arrays.asList(arr), count);
    }

    public Linq<T> skipWhile(IFunc<T, Boolean> iFunc) {
        this._list = Linq.skipWhile(this._list, iFunc);
        return this;
    }

    public static <T> List<T> skipWhile(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (!iFunc.func(next)) {
                list.add(next);
                break;
            }
        }
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <T> List<T> skipWhile(Collection<T> collection, IFunc<T, Boolean> iFunc) {
        List<T> list = new ArrayList<>(collection.size());
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (!iFunc.func(next)) {
                list.add(next);
                break;
            }
        }
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <T> List<T> skipWhile(T[] arr, IFunc<T, Boolean> iFunc) {
        return Linq.skipWhile(Arrays.asList(arr), iFunc);
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

    public Linq<T> take(int count) {
        if (this.count() > count) {
            this._list = Linq.take(this, count);
        }
        return this;
    }

    public static <T> List<T> take(Iterable<T> iterable, int count) {
        List<T> list = new ArrayList<>(count);
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext() && count-- > 0) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <T> List<T> take(T[] arr, int count) {
        return Linq.take(Arrays.asList(arr), count);
    }

    public Linq<T> takeLast(int count) {
        if (count <= 0) {
            this._list.clear();
            return this;
        } else if (this.count() <= count) {
            return this;
        }
        List<T> oldList = this._list;
        List<T> newList = new ArrayList<>(count);
        for (int i = oldList.size() - count; i < oldList.size(); i++) {
            newList.add(oldList.get(i));
        }
        this._list = newList;
        return this;
    }

    public static <T> List<T> takeLast(Iterable<T> iterable, int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }
        Stack<T> stack = new Stack<>();
        for (var item : iterable) {
            stack.push(item);
        }
        List<T> list = new ArrayList<>(count);
        while (!stack.empty() && count-- > 0) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        return list;
    }

    public static <T> List<T> takeLast(T[] arr, int count) {
        return Linq.takeLast(Arrays.asList(arr), count);
    }

    public Linq<T> takeWhile(IFunc<T, Boolean> iFunc) {
        this._list = Linq.takeWhile(this._list, iFunc);
        return this;
    }

    public static <T> List<T> takeWhile(Iterable<T> iterable, IFunc<T, Boolean> iFunc) {
        List<T> list = new ArrayList<>();
        for (var item : iterable) {
            if (iFunc.func(item)) {
                list.add(item);
            } else {
                break;
            }
        }
        return list;
    }

    public static <T> List<T> takeWhile(Collection<T> collection, IFunc<T, Boolean> iFunc) {
        List<T> list = new ArrayList<>(collection.size());
        for (var item : collection) {
            if (iFunc.func(item)) {
                list.add(item);
            } else {
                break;
            }
        }
        return list;
    }

    public static <T> List<T> takeWhile(T[] arr, IFunc<T, Boolean> iFunc) {
        return Linq.takeWhile(Arrays.asList(arr), iFunc);
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

    public <KeyType> Map<KeyType, T> toMap(IFunc<T, KeyType> iFunc) {
        return Linq.toMap(this._list, iFunc);
    }

    public static <K, V> Map<K, V> toMap(Iterable<V> iterable, IFunc<V, K> iFunc) {
        Map<K, V> map = new HashMap<>();
        for (var item : iterable) {
            map.put(iFunc.func(item), item);
        }
        return map;
    }

    public static <K, V> Map<K, V> toMap(Collection<V> collection, IFunc<V, K> iFunc) {
        Map<K, V> map = new HashMap<>(collection.size());
        for (var item : collection) {
            map.put(iFunc.func(item), item);
        }
        return map;
    }

    public static <K, V> Map<K, V> toMap(V[] arr, IFunc<V, K> iFunc) {
        return Linq.toMap(Arrays.asList(arr), iFunc);
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

    public Linq<T> union(Iterable<T> iterable, IEqualityComparator<T> equalityComparator) {
        return this.concat(iterable).distinct(equalityComparator);
    }

    public Linq<T> union(T[] arr, IEqualityComparator<T> equalityComparator) {
        return this.concat(arr).distinct(equalityComparator);
    }

    public static <T>
    List<T> union(Iterable<T> iterable1, Iterable<T> iterable2, IEqualityComparator<T> equalityComparator) {
        List<T> list = Linq.concat(iterable1, iterable2);
        return Linq.distinct(list, equalityComparator);
    }

    public static <T> List<T> union(T[] arr, Iterable<T> iterable, IEqualityComparator<T> equalityComparator) {
        return Linq.union(Arrays.asList(arr), iterable, equalityComparator);
    }

    public static <T> List<T> union(Iterable<T> iterable, T[] arr, IEqualityComparator<T> equalityComparator) {
        return Linq.union(iterable, Arrays.asList(arr), equalityComparator);
    }

    public static <T> List<T> union(T[] arr1, T[] arr2, IEqualityComparator<T> equalityComparator) {
        return Linq.union(Arrays.asList(arr1), Arrays.asList(arr2), equalityComparator);
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

    public <ResultType> Linq<ResultType> zip(Iterable<T> iterable, IZipFunc<T, ResultType> zipFunc) {
        return this.zip(Linq.toList(iterable), zipFunc);
    }

    public <ResultType> Linq<ResultType> zip(List<T> list, IZipFunc<T, ResultType> zipFunc) {
        List<ResultType> newList = Linq.zip(this._list, list, zipFunc);
        Linq<ResultType> linq = new Linq<>();
        linq._list = newList;
        return linq;
    }

    public <ResultType> Linq<ResultType> zip(T[] arr, IZipFunc<T, ResultType> zipFunc) {
        return this.zip(Arrays.asList(arr), zipFunc);
    }

    public static <SourceType, ResultType>
    List<ResultType> zip(List<SourceType> list1, List<SourceType> list2, IZipFunc<SourceType, ResultType> zipFunc) {
        int size = Math.min(list1.size(), list2.size());
        List<ResultType> newList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            newList.add(zipFunc.zip(list1.get(i), list2.get(i)));
        }
        return newList;
    }

    public static <SourceType, ResultType>
    List<ResultType> zip(Iterable<SourceType> it1, Iterable<SourceType> it2, IZipFunc<SourceType, ResultType> zipFunc) {
        return Linq.zip(Linq.toList(it1), Linq.toList(it2), zipFunc);
    }

    public static <SourceType, ResultType>
    List<ResultType> zip(Iterable<SourceType> iterable, SourceType[] arr, IZipFunc<SourceType, ResultType> zipFunc) {
        return Linq.zip(Linq.toList(iterable), Arrays.asList(arr), zipFunc);
    }

    public static <SourceType, ResultType>
    List<ResultType> zip(SourceType[] arr, Iterable<SourceType> iterable, IZipFunc<SourceType, ResultType> zipFunc) {
        return Linq.zip(Arrays.asList(arr), Linq.toList(iterable), zipFunc);
    }

    public static <SourceType, ResultType>
    List<ResultType> zip(SourceType[] arr1, SourceType[] arr2, IZipFunc<SourceType, ResultType> zipFunc) {
        return Linq.zip(Arrays.asList(arr1), Arrays.asList(arr2), zipFunc);
    }
}
