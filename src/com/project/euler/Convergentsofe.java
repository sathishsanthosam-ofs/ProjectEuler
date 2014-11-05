package com.project.euler;


public class Convergentsofe
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        BigFraction previous = BigFraction.ZERO;
        for (int i = 1; i < 8; i++)
        {
            final BigFraction current = BigFraction.ONE.divide(getFraction(i).add(previous));
            previous = current;
            final BigFraction val = BigFraction.TWO.add(current);
            System.out.println(val);

        }
        // final int upperbound = 100;
        // final int result = 0;
        //
        // BigInteger d = BigInteger.ONE;
        // BigInteger n = new BigInteger("2");
        //
        // for (int i = 2; i <= upperbound; i++)
        // {
        // final BigInteger temp = d;
        // final int c = (i % 3 == 0) ? 2 * (i / 3) : 1;
        // d = n;
        // n = d.multiply(new BigInteger("" + c)).add(temp);
        // System.out.println(n);
        // }
        // System.out.println(n);
    }

    private static BigFraction getFraction(final int n)
    {
        if ((n % 3) == 2)
        {
            final BigFraction ret = BigFraction.TWO.multiply((n / 3) + 1);
            return ret;
        }
        else
        {
            return BigFraction.ONE;
        }
    }

}
