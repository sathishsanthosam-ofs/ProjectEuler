package com.project.euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public class DistinctPowers
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final Set<String> retSet = new HashSet<String>();
        // final Map<Integer, Integer> power = new HashMap<Integer, Integer>();
        BigInteger ret = null;
        final BigInteger max = new BigInteger("" + 100);
        for (int i = 2; i <= max.intValue(); i++)
        {
            // final int start = power.get(i) == null ? 2 : (max.intValue() / power.get(i));
            for (int j = 2; j <= max.intValue(); j++)
            {
                ret = new BigInteger("" + i);
                BigInteger val = ret.pow(j);
                retSet.add(val.toString());
                ret = null;
                val = null;
            }
        }
        System.out.println(retSet.size());

    }
}
