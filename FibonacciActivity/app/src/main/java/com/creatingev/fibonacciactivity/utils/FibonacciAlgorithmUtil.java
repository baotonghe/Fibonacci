package com.creatingev.fibonacciactivity.utils;

/**
 * Algorithm Of Fibonacci
 *
 * @author hebaotong
 * @version 1.0.0
 * @date 2015.10.25
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FibonacciAlgorithmUtil {
    private static List<BigInteger> mSavedFibonacci = new ArrayList<>(); // save calc fibonacci

    public static BigInteger fibonacciDynamicProgramming(int n) {
        if (0 == n) {
            mSavedFibonacci.add(BigInteger.ZERO);
            return BigInteger.ZERO;
        }

        if (1 == n) {
            mSavedFibonacci.add(BigInteger.ONE);
            return BigInteger.ONE;
        }

        for (int i = mSavedFibonacci.size(); i <= n; ++i) {
            mSavedFibonacci.add(mSavedFibonacci.get(i - 2).add(mSavedFibonacci.get(i - 1)));
        }

        return mSavedFibonacci.get(n);
    }
}
