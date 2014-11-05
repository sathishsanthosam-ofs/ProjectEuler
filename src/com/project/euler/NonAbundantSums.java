package com.project.euler;

import java.util.ArrayList;
import java.util.List;

public class NonAbundantSums
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<Integer> abdundantList = new ArrayList<Integer>();
        long ret = 0;
        for (int i = 1; i < 28124; i++)
        {
            if (sumOfDivisiors(i) > i)
            {
                abdundantList.add(i);
            }
            if (!isAbduantSum(abdundantList, i))
            {
                ret = ret + i;
            }
        }
        System.out.println(ret);
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

    private static boolean isAbduantSum(final List<Integer> abdundantList, final int no)
    {
        Integer remainder = null;
        for (final Integer integer : abdundantList)
        {
            remainder = new Integer(no - integer.intValue());
            if (abdundantList.contains(remainder))
            {
                return true;
            }
        }
        remainder = null;
        return false;
    }

}
