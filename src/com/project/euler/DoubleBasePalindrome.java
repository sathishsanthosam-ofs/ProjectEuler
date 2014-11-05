package com.project.euler;

import java.math.BigInteger;

public class DoubleBasePalindrome
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int d = 0;
        for (int i = 1000000; i >= 1; i--)
        {
            if (isPalindeome("" + i))
            {
                System.out.println("Base 10 : " + i);
                // d++;
                final String s = getBinaryValue("" + i);
                if (isPalindeome(s))
                {
                    System.out.println("Base 10 : " + i + " Base 2 : " + s);
                    d = d + i;
                }

            }
        }
        System.out.println(d);
    }

    private static boolean isPalindeome(final String value)
    {
        BigInteger ori = new BigInteger(value);
        BigInteger ret = BigInteger.ZERO;
        BigInteger quotient = null;
        BigInteger reminder = null;
        while ((quotient = ori.divide(BigInteger.TEN)).compareTo(BigInteger.ZERO) >= 1)
        {
            reminder = ori.remainder(BigInteger.TEN);
            ret = ret.add(reminder.multiply(BigInteger.TEN.pow(quotient.toString().length())));
            ori = quotient;
        }
        ret = ret.add(ori);
        return ret.toString().equals(value);
    }

    private static String getBinaryValue(final String value)
    {
        final StringBuffer buf = new StringBuffer();
        int ori = Integer.parseInt(value);
        int quotient = 0;
        int reminder = 0;
        if (ori == 0 || ori == 1)
        {
            return ori + "";
        }
        while ((quotient = (ori / 2)) > 0)
        {
            reminder = (ori % 2);
            buf.insert(0, "" + reminder);
            ori = quotient;
            if (quotient == 1)
            {
                buf.insert(0, "" + quotient);
            }
        }
        return buf.toString();
    }

}
