package com.project.euler;

import java.math.BigInteger;

public class CombinatoricSelections
{

    private static BigInteger two = new BigInteger("2");

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int count = 0;
        final BigInteger million = new BigInteger("1000000");
        for (int i = 1; i <= 100; i++)
        {
            final BigInteger n = new BigInteger("" + i);
            for (int j = 0; j <= i; j++)
            {
                final BigInteger r = new BigInteger("" + j);
                final BigInteger comp = factorial(n).divide((factorial(r).multiply(factorial(n.subtract(r)))));
                if (comp.compareTo(million) > 0)
                {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static BigInteger factorial(final BigInteger n)
    {
        if (n.equals(BigInteger.ZERO))
        {
            return BigInteger.ONE;
        }
        else if (n.equals(BigInteger.ONE))
        {
            return BigInteger.ONE;
        }
        else if (n.equals(two))
        {
            return two;
        }
        else
        {
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
        }
    }

}
