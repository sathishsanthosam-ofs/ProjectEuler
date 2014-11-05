package com.project.euler;

import java.math.BigInteger;


public class Fibanacci
{

    public static void main(final String[] args)
    {
        // final List<Long> fibonacci = new ArrayList<Long>();
        // fibonacci.add((long) 1);
        // fibonacci.add((long) 2);
        // long currentValue = 0;
        // long ret = 2;
        // while (currentValue < 4000000)
        // {
        // final int size = fibonacci.size();
        // currentValue = fibonacci.get(size - 2) + fibonacci.get(size - 1);
        // if (currentValue < 4000000)
        // {
        // fibonacci.add(currentValue);
        // if ((currentValue % 2) == 0)
        // {
        // ret = ret + currentValue;
        // }
        // }
        // }
        // System.out.println(ret);
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        BigInteger c = a.add(b);
        boolean isNeedToRecures = true;
        int index = 1;
        while (isNeedToRecures)
        {
            a = b.add(c);
            b = c.add(a);
            c = a.add(b);
            isNeedToRecures = (c.toString().length() < 1000);
            index++;
        }
        System.out.println(index * 3);
    }
}
