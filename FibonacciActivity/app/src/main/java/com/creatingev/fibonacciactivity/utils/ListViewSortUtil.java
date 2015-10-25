package com.creatingev.fibonacciactivity.utils;

/**
 * Sort Util Of ListView
 *
 * @author hebaotong
 * @version 1.0.0
 * @date 2015.10.25
 */

import com.creatingev.fibonacciactivity.service.Fibonacci;

import java.util.Comparator;

public class ListViewSortUtil {

    public static Comparator<Fibonacci> AscComparator = new Comparator<Fibonacci>() {
        public int compare(Fibonacci fibo1, Fibonacci fibo2) {
            if (fibo1.getParameter() > fibo2.getParameter()) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    public static Comparator<Fibonacci> DescComparator = new Comparator<Fibonacci>() {
        public int compare(Fibonacci fibo1, Fibonacci fibo2) {
            if (fibo1.getParameter() < fibo2.getParameter()) {
                return 1;
            } else {
                return -1;
            }
        }
    };
}
