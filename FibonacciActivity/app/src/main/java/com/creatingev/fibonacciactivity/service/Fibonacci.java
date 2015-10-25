package com.creatingev.fibonacciactivity.service;

/**
 * Fibonacci
 *
 * @author hebaotong
 * @version 1.0.0
 * @date 2015.10.25
 */

import com.creatingev.fibonacciactivity.utils.FibonacciAlgorithmUtil;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class Fibonacci {
    private int mParameter;
    private int mArgument;
    private BigInteger mValue;
    private String mStrFibonacci;

    public Fibonacci() {
    }

    public int getParameter() {
        return mParameter;
    }

    public void setParameter(int mParameter) {
        this.mParameter = mParameter;
        mArgument = (int) Math.pow(mParameter, 2);
    }

    public String getFibonacci() {
        return mStrFibonacci;
    }

    public void calcFibonacci() throws RuntimeException {
        this.mStrFibonacci = "F("
                                + getFormatParameter()
                                + ") = "
                                + getFormatValue()
                                + ";";
    }

    private String getFormatParameter() {
        return mParameter + "^2 = " + mArgument;
    }

    private String getFormatValue() {
        mValue = FibonacciAlgorithmUtil.fibonacciDynamicProgramming(mArgument);

        BigInteger tenPowTen = BigInteger.TEN.pow(10);
        if (mValue.compareTo(tenPowTen) > 0) {
            DecimalFormat formatter = new DecimalFormat("0.0E0");
            formatter.setMinimumFractionDigits(2);
            return formatter.format(mValue);
        }
        else {
            return mValue.toString();
        }
    }
}
