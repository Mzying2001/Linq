package com.mzying2001.linq;

import com.mzying2001.linq.interfaces.IComparator;
import com.mzying2001.linq.interfaces.IFunc;

public class OrderedLinq<T> extends Linq<T> {
    private final IComparator<T> _comparator;

    private OrderedLinq(Linq<T> linq, IComparator<T> comparator) {
        super();
        this._list = linq._list;
        this._comparator = comparator;
    }

    public static <T> OrderedLinq<T> create(Linq<T> linq, IComparator<T> comparator) {
        return new OrderedLinq<>(linq, comparator);
    }

    public static <T> OrderedLinq<T> create(OrderedLinq<T> orderedLinq, IComparator<T> comparator) {
        return new OrderedLinq<>(orderedLinq, (a, b) -> {
            int first = orderedLinq._comparator.compare(a, b);
            return first == 0 ? comparator.compare(a, b) : first;
        });
    }

    public static <T, ComparableType extends Comparable<ComparableType>>
    OrderedLinq<T> create(Linq<T> linq, IFunc<T, ComparableType> iFunc) {
        return new OrderedLinq<>(linq, (a, b) -> iFunc.func(a).compareTo(iFunc.func(b)));
    }

    public static <T, ComparableType extends Comparable<ComparableType>>
    OrderedLinq<T> create(OrderedLinq<T> orderedLinq, IFunc<T, ComparableType> iFunc) {
        return new OrderedLinq<>(orderedLinq, (a, b) -> {
            int first = orderedLinq._comparator.compare(a, b);
            return first == 0 ? iFunc.func(a).compareTo(iFunc.func(b)) : first;
        });
    }

    public <ComparableType extends Comparable<ComparableType>>
    OrderedLinq<T> thenBy(IFunc<T, ComparableType> iFunc) {
        OrderedLinq<T> ret = OrderedLinq.create(this, iFunc);
        Sort.insertSort(ret._list, ret._comparator);
        return ret;
    }

    public <ComparableType extends Comparable<ComparableType>>
    OrderedLinq<T> thenByDescending(IFunc<T, ComparableType> iFunc) {
        IComparator<T> comparator = (a, b) -> -iFunc.func(a).compareTo(iFunc.func(b));
        OrderedLinq<T> ret = OrderedLinq.create(this, comparator);
        Sort.insertSort(ret._list, ret._comparator);
        return ret;
    }
}
