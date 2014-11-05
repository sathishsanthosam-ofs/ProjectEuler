package com.project.euler;


public class DigitFactorial
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int ret = 0;
        for (int i = 3; i < 10000000; i++)
        {
            int total = 0;
            final String s = "" + i;
            for (int j = 0; j < s.length(); j++)
            {
                total = total + factorial(Integer.parseInt("" + s.charAt(j)));
            }
            if (total == Integer.parseInt(s))
            {
                ret = ret + i;
            }
        }

        System.out.println(ret);
    }

    private static int factorial(final int n)
    {
        if (n == 0)
        {
            return 1;
        }
        else if (n == 2 || n == 1)
        {
            return n;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }

}
