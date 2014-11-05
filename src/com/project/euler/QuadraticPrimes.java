package com.project.euler;

public class QuadraticPrimes
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int maxPrime = 0;
        int ret = 0;
        for (int i = -1000; i < 1000; i++)
        {
            for (int j = -79; j < 1602; j++)
            {
                for (int n = 0;; n++)
                {
                    if (!isPrime((n * n) + (j * n) + i))
                    {
                        if (maxPrime < n)
                        {
                            maxPrime = n;
                            ret = (i * j);
                            System.out.println(" A is" + i + " B is " + j + " Count : " + maxPrime);
                        }
                        break;
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
