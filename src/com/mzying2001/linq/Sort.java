package com.mzying2001.linq;

import com.mzying2001.linq.interfaces.ICompare;
import com.mzying2001.linq.interfaces.IFunc;

import java.util.List;

public class Sort {
    public static <T, ComparableType extends Comparable<ComparableType>>
    void quickSort(List<T> list, IFunc<T, ComparableType> iFunc, int low, int high) {
        if (low >= high)
            return;

        int l = low;
        int h = high;
        T tmp = list.get(high);

        while (l < h) {
            while (l < h && iFunc.func(list.get(l)).compareTo(iFunc.func(tmp)) <= 0) {
                l++;
            }
            if (l < h) {
                list.set(h--, list.get(l));
            }

            while (l < h && iFunc.func(list.get(h)).compareTo(iFunc.func(tmp)) >= 0) {
                h--;
            }
            if (l < h) {
                list.set(l++, list.get(h));
            }
        }

        list.set(l, tmp);
        quickSort(list, iFunc, low, l - 1);
        quickSort(list, iFunc, l + 1, high);
    }

    public static <T, ComparableType extends Comparable<ComparableType>>
    void quickSort(List<T> list, IFunc<T, ComparableType> iFunc) {
        if (list.size() > 1)
            quickSort(list, iFunc, 0, list.size() - 1);
    }

    public static <T> void quickSort(List<T> list, ICompare<T> comparator, int low, int high) {
        if (low >= high)
            return;

        int l = low;
        int h = high;
        T tmp = list.get(high);

        while (l < h) {
            while (l < h && comparator.compare(list.get(l), tmp) <= 0) {
                l++;
            }
            if (l < h) {
                list.set(h--, list.get(l));
            }

            while (l < h && comparator.compare(list.get(h), tmp) >= 0) {
                h--;
            }
            if (l < h) {
                list.set(l++, list.get(h));
            }
        }

        list.set(l, tmp);
        quickSort(list, comparator, low, l - 1);
        quickSort(list, comparator, l + 1, high);
    }

    public static <T> void quickSort(List<T> list, ICompare<T> comparator) {
        if (list.size() > 1)
            quickSort(list, comparator, 0, list.size() - 1);
    }
}
