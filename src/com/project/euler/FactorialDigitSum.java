package com.project.euler;

import java.math.BigInteger;

public class FactorialDigitSum
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        args = new String[10];
        final String value = factorial(new BigInteger("100")).toString();
        int ret = 0;
        for (int i = 0; i < value.length(); i++)
        {
            ret = ret + Integer.parseInt("" + value.charAt(i));
        }
        System.out.println(ret);
    }

    private static BigInteger factorial(final BigInteger n)
    {
        if (n.intValue() == 2)
        {
            return new BigInteger("2");
        }
        else
        {
            return n.multiply(factorial(n.subtract(new BigInteger("1"))));
        }
    }

    // private static long countValue()

}
