package com.project.euler;

import java.util.ArrayList;
import java.util.List;

public class SprialPrimes
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<Integer> diaList = new ArrayList<Integer>();
        diaList.add(1);
        diaList.add(3);
        diaList.add(5);
        diaList.add(7);
        diaList.add(9);
        diaList.add(13);
        diaList.add(17);
        diaList.add(21);
        diaList.add(25);
        diaList.add(31);
        diaList.add(37);
        diaList.add(43);
        diaList.add(49);
        final List<Integer> primeList = new ArrayList<Integer>();
        primeList.add(3);
        primeList.add(5);
        primeList.add(7);
        primeList.add(13);
        primeList.add(17);
        primeList.add(31);
        primeList.add(37);
        primeList.add(43);
        int n = 7;
        int i = 49;
        while ((((float) primeList.size() / (float) diaList.size()) * 100) > 10)
        {
            n = n + 2;
            final int sideSquare = n * n;
            while (i < sideSquare)
            {
                i = i + (n - 1);
                diaList.add(i);
                if (isPrime(i))
                {
                    primeList.add(i);
                }
            }
        }
        System.out.println(n);

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
