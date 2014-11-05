package com.project.euler;

import java.math.BigInteger;

public class LychrelNumbers
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int count = 0;
        System.out.println(isPalindeome(new BigInteger("121")));
        for (int i = 1; i < 10000; i++)
        {
            int iteration = 1;
            BigInteger val = new BigInteger("" + i).add(new BigInteger(reverse("" + i)));
            boolean reached = isPalindeome(val);
            while (!reached)
            {
                iteration++;
                val = val.add(new BigInteger(reverse(val.toString())));
                reached = isPalindeome(val);
                if (!reached)
                {
                    if (iteration >= 50)
                    {
                        count++;
                        reached = true;
                    }
                }
            }
        }
        System.out.println(count);

    }

    private static boolean isPalindeome(final BigInteger input)
    {
        if (input.toString().length() > 1)
        {
            BigInteger ori = input;
            BigInteger ret = BigInteger.ZERO;
            BigInteger quotient = BigInteger.ZERO;
            BigInteger reminder = BigInteger.ZERO;
            while ((quotient = (ori.divide(BigInteger.TEN))).compareTo(BigInteger.ZERO) > 0)
            {
                reminder = (ori.remainder(BigInteger.TEN));
                ret = ret.add(reminder.multiply(BigInteger.TEN.pow(quotient.toString().length())));
                ori = quotient;
            }
            ret = ret.add(ori);
            return ret.compareTo(input) == 0;
        }
        else
        {
            return false;
        }

    }

    private static String reverse(final String no)
    {
        final StringBuffer ret = new StringBuffer(no);
        return ret.reverse().toString();
    }

}
