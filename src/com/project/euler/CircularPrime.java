package com.project.euler;

public class CircularPrime
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int ret = 0;
        for (int i = 100000; i > 1; i--)
        {
            if (isPrime(i))
            {
                final String s = "" + i;
                if (s.length() == 1)
                {
                    ret++;
                    // System.out.print(i);
                }
                else
                {
                    boolean isCiruclarPrime = true;
                    for (int j = 0; j < s.length(); j++)
                    {
                        final String s1 = s.substring(j) + s.substring(0, j);
                        if (!isPrime(Integer.parseInt(s1)))
                        {
                            isCiruclarPrime = false;
                            break;
                        }
                    }
                    if (isCiruclarPrime)
                    {
                        ret++;
                        // System.out.print(i);
                    }
                }

            }
        }
        System.out.println(ret);
    }

    private static boolean isPrime(int n)
    {
        if (n < 0)
        {
            n = (-1 * n);
        }
        final int max = (int) Math.sqrt(n);
        for (int i = 2; i <= max; i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

}
