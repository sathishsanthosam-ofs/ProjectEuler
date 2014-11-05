package com.project.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PentagonNumbers
{

    final public static BigInteger two = new BigInteger("2");

    final public static BigInteger three = new BigInteger("3");

    final public static BigInteger four = new BigInteger("4");

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<BigInteger> penList = new ArrayList<BigInteger>();
        for (int i = 1;; i++)
        {
            final BigInteger cur = new BigInteger("" + i);
            final BigInteger pen = cur.multiply(cur.multiply(three).subtract(BigInteger.ONE)).divide(two);
            for (final BigInteger bigInteger : penList)
            {
                final BigInteger diff = pen.subtract(bigInteger);
                if (penList.contains(diff))
                {
                    final BigInteger add = pen.add(bigInteger);
                    if (ispen(add))
                    {
                        System.out.println(diff.toString());
                        break;
                    }
                }
            }
            penList.add(pen);

        }
    }

    private static boolean ishex(final BigInteger value)
    {
        return quartraticEquation(two, BigInteger.ONE.negate(), value.negate(), value);
    }

    private static boolean ispen(final BigInteger value)
    {
        return quartraticEquation(three, BigInteger.ONE.negate(), value.multiply(two).negate(), value);
    }

    private static boolean quartraticEquation(final BigInteger a, final BigInteger b, final BigInteger c,
            final BigInteger result)
    {
        final boolean ret = false;
        final BigInteger d = bigIntSqRootCeil(b.pow(2).subtract(a.multiply(c).multiply(four)));
        final BigInteger first = d.subtract(b).divide(a.multiply(two));
        final BigInteger second = d.add(b).divide(a.multiply(two));
        if (first.compareTo(BigInteger.ZERO) > 0)
        {
            if (a.equals(three))
            {
                final BigInteger val = first.multiply(first.multiply(three).subtract(BigInteger.ONE)).divide(two);
                if (val.toString().equals(result.toString()))
                {
                    return true;
                }
            }
            else
            {
                final BigInteger val = first.multiply(first.multiply(two).subtract(BigInteger.ONE));
                if (val.toString().equals(result.toString()))
                {
                    return true;
                }
            }
        }
        if (second.compareTo(BigInteger.ZERO) > 0)
        {
            if (a.equals(three))
            {
                final BigInteger val = first.multiply(first.multiply(three).subtract(BigInteger.ONE)).divide(two);
                if (val.toString().equals(result.toString()))
                {
                    return true;
                }
            }
            else
            {
                final BigInteger val = first.multiply(first.multiply(two).subtract(BigInteger.ONE));
                if (val.toString().equals(result.toString()))
                {
                    return true;
                }
            }
        }

        return ret;
    }

    public static BigInteger bigIntSqRootCeil(final BigInteger x)
            throws IllegalArgumentException
    {
        if (x.compareTo(BigInteger.ZERO) < 0)
        {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x == BigInteger.ZERO || x == BigInteger.ONE)
        {
            return x;
        } // end if
        final BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two); y.compareTo(x.divide(y)) > 0; y = ((x.divide(y)).add(y)).divide(two))
        {
            ;
        }
        if (x.compareTo(y.multiply(y)) == 0)
        {
            return y;
        }
        else
        {
            return BigInteger.ONE;
        }
    }

}
