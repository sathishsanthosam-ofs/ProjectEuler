package com.project.euler;

public class Palindrome
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final int max = (999 * 999);
        final int low = (100 * 100);

        for (int i = max; i >= low; i--)
        {
            if (isPalindeome("" + i))
            {
                for (int j = 100; j < 1000; j++)
                {
                    if ((i % j) == 0 && ((i / j) > 99) && ((i / j) < 1000))
                    {
                        System.out.println(j + " * " + (i / j) + " = " + i);
                        break;
                    }
                }
            }
        }
    }

    private static boolean isPalindeome(final String value)
    {
        int ori = Integer.parseInt(value);
        int ret = 0;
        int quotient = 0;
        int reminder = 0;
        while ((quotient = (ori / 10)) > 0)
        {
            reminder = (ori % 10);
            ret = (int) (ret + (reminder * Math.pow(10, ("" + quotient).length())));
            ori = quotient;
        }
        ret = ret + ori;
        return ret == Integer.parseInt(value);
    }
}
