package com.project.euler;

import java.math.BigInteger;

public class PowerDigitSum
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final BigInteger two = new BigInteger("2");
        final String value = two.pow(1000).toString();
        int ret = 0;
        for (int i = 0; i < value.length(); i++)
        {
            ret = ret + Integer.parseInt("" + value.charAt(i));
        }
        System.out.println(ret);

    }

}
