package com.project.euler;

import java.util.HashMap;
import java.util.Map;


public class AmicableNumbers
{
    public static void main(final String[] args)
    {
        final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int i = 2; i <= 10000; i++)
        {
            map.put(i, sumOfDivisiors(i));
        }
        for (final Integer no : map.keySet())
        {
            if (!no.equals(map.get(no)) && no.equals(map.get(map.get(no))) && map.get(map.get(no)) <= 10000)
            {
                ret = ret + no;
            }
        }

    }

    private static int sumOfDivisiors(final int n)
    {
        int ret = 0;
        for (int i = 1; i <= (n / 2); i++)
        {
            if ((n % i) == 0)
            {
                ret = ret + i;
            }
        }
        return ret;
    }
}
