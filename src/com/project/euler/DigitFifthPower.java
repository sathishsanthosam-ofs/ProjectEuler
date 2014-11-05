package com.project.euler;

public class DigitFifthPower
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int ret = 0;
        for (int i = 2; i < 100000000; i++)
        {
            int total = 0;
            final String s = "" + i;
            for (int j = 0; j < s.length(); j++)
            {
                final int n = Integer.parseInt("" + s.charAt(j));
                total = (int) (total + Math.pow(n, 5));
            }
            if (total == i)
            {
                ret = ret + total;
            }
        }
        System.out.println(ret);
    }

}
