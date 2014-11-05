package com.project.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PandigitalPrime
{

    final public static BigInteger two = new BigInteger("2");

    final public static BigInteger three = new BigInteger("3");

    final public static BigInteger four = new BigInteger("4");

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        BigInteger result = null;
        String s = "123";
        System.out.println(isPrime(new BigInteger("2143")));
        for (int i = 4; i < 10; i++)
        {
            final List<BigInteger> ret = new ArrayList<BigInteger>();
            swapValues("", s + i, ret);
            if (ret.size() > 0)
            {
                result = ret.get(0);
            }
            s = s + i;
        }
        System.out.println(result.toString());
    }

    private static void swapValues(final String text, final String val, final List<BigInteger> ret)
    {
        if (val.length() == 2)
        {
            BigInteger n = new BigInteger(text + val);
            if (isPrime(n))
            {
                if (ret.size() == 0)
                {
                    ret.add(n);
                }
                else
                {
                    if (n.compareTo(ret.get(0)) > 0)
                    {
                        ret.set(0, n);
                    }
                }
            }
            n = new BigInteger(text + val.charAt(1) + val.charAt(0));
            if (isPrime(n))
            {
                if (ret.size() == 0)
                {
                    ret.add(n);
                }
                else
                {
                    if (n.compareTo(ret.get(0)) > 0)
                    {
                        ret.set(0, n);
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < val.length(); i++)
            {
                swapValues(text + val.charAt(i), val.substring(i + 1) + val.substring(0, i), ret);
            }
        }
    }

    public static boolean isPrime(BigInteger n)
    {
        if (n.compareTo(BigInteger.ZERO) < 0)
        {
            n = (n.multiply(BigInteger.ONE.negate()));
        }
        if (n.compareTo(BigInteger.ONE) == 0)
        {
            return false;
        }
        final BigInteger max = TriPenHex.bigIntSqRootCeil(n);
        for (BigInteger i = two;; i = i.add(BigInteger.ONE))
        {
            if (n.remainder(i).compareTo(BigInteger.ZERO) == 0)
            {
                return false;
            }
            if (max.compareTo(i) == 0)
            {
                break;
            }
        }
        return true;
    }

}
