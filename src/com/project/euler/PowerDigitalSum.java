package com.project.euler;

import java.math.BigInteger;

public class PowerDigitalSum
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        BigInteger max = BigInteger.ZERO;
        for (int i = 1; i < 100; i++)
        {
            final BigInteger a = new BigInteger("" + i);
            for (int j = 1; j < 100; j++)
            {
                final String s = a.pow(j).toString();
                BigInteger sum = BigInteger.ZERO;
                for (int k = 0; k < s.length(); k++)
                {
                    sum = sum.add(new BigInteger("" + s.charAt(k)));
                }
                if (max.compareTo(sum) == -1)
                {
                    max = sum;
                }
            }
        }
        System.out.println(max);
    }
}
