package com.project.euler;

public class TruncatablePrimes
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int count = 0;
        int ret = 0;
        for (int i = 11;; i = i + 2)
        {
            if (isPrime(i))
            {
                final String s = "" + i;
                boolean isTruncatablePrime = true;
                for (int j = 1; j < s.length(); j++)
                {
                    final String s1 = s.substring(j);
                    if (!isPrime(Integer.parseInt(s1)))
                    {
                        isTruncatablePrime = false;
                        break;
                    }
                }
                if (isTruncatablePrime)
                {
                    for (int j = s.length() - 1; j > 0; j--)
                    {
                        final String s1 = s.substring(0, j);
                        if (!isPrime(Integer.parseInt(s1)))
                        {
                            isTruncatablePrime = false;
                            break;
                        }
                    }
                }
                if (isTruncatablePrime)
                {
                    System.out.println(i);
                    count++;
                    ret = ret + i;
                    if (count == 11)
                    {
                        break;
                    }
                }
            }
        }
        System.out.println(ret);
    }

    public static boolean isPrime(int n)
    {
        if (n < 0)
        {
            n = (-1 * n);
        }
        if (n == 1)
        {
            return false;
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
