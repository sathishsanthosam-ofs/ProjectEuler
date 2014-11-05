package com.project.euler;

import java.util.HashMap;
import java.util.Map;

public class CollatzSequence
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final Map<Long, Long> collatzMap = new HashMap<Long, Long>();
        long maxcount = 0;
        long maxNumber = 0;
        for (int i = 2; i < 1000000; i++)
        {
            final long count = findNoOfCollatzChain(i, collatzMap);
            if (count > maxcount)
            {
                maxcount = count;
                maxNumber = i;
            }
        }

        System.out.println(maxNumber);
        System.out.println(maxcount);
    }

    public static long findNoOfCollatzChain(final long n, final Map<Long, Long> collatzMap)
    {
        long ret = 0;
        if (collatzMap.containsKey(n))
        {
            return collatzMap.get(n);
        }
        if ((n % 2) == 0)
        {
            if ((n / 2) == 1)
            {
                collatzMap.put(n, 2l);
                return 2;
            }
            else
            {
                ret = findNoOfCollatzChain((n / 2), collatzMap) + 1;
                collatzMap.put(n, ret);
            }
        }
        else
        {
            ret = findNoOfCollatzChain(((3 * n) + 1), collatzMap) + 1;
            collatzMap.put(n, ret);
        }
        return ret;
    }

}
