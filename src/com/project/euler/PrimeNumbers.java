package com.project.euler;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final long max = 2000000;
        int current = 1;
        long currentValue = 1l;
        long ret = 5l;
        final List<Long> primeList = new ArrayList<Long>();
        primeList.add(3l);
        while (currentValue < max)
        {
            final long last = primeList.get(current - 1);
            for (long i = last;; i = i + 2)
            {
                boolean isPrime = true;
                for (final Long prime : primeList)
                {
                    if ((i % prime) == 0)
                    {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime)
                {
                    if (i < max)
                    {
                        primeList.add(i);
                        ret = ret + i;
                    }

                    current++;
                    currentValue = i;
                    break;
                }
            }
        }
        System.out.println(primeList.get(primeList.size() - 1));
        System.out.println(ret);

        // for (int i = primeList.size() - 1; i > 0; i--)
        // {
        // if ((600851475143l % primeList.get(i)) == 0)
        // {
        // System.out.println(primeList.get(i));
        // break;
        // }
        // }
    }
}
