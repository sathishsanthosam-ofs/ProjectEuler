package com.project.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistinctPrimesFactors
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<Integer> prime = new ArrayList<Integer>();
        prime.add(2);
        prime.add(3);
        prime.add(5);
        prime.add(7);
        final List<Integer> firstPrime = new ArrayList<Integer>();
        final List<Integer> secondPrime = new ArrayList<Integer>();
        final List<Integer> thirdPrime = new ArrayList<Integer>();
        final List<Integer> fourthPrime = new ArrayList<Integer>();
        final Map<Integer, List<Integer>> factorMap = new HashMap<Integer, List<Integer>>();
        factorMap.put(0, firstPrime);
        factorMap.put(1, secondPrime);
        factorMap.put(2, thirdPrime);
        factorMap.put(3, fourthPrime);

        int count = -1;
        for (int i = 8;; i++)
        {
            if (TruncatablePrimes.isPrime(i))
            {
                prime.add(i);
                count = -1;
            }
            else
            {
                count++;
                final Map<Integer, Integer> multiplyMap = new HashMap<Integer, Integer>();
                int quoent = i;
                while (quoent != 1)
                {
                    for (final Integer integer : prime)
                    {
                        if (quoent % integer == 0)
                        {
                            quoent = (quoent / integer);
                            multiplyMap.put(
                                    integer,
                                    adddfactor(factorMap, count, integer,
                                            multiplyMap.keySet().contains(integer) ? multiplyMap.get(integer) : 0));
                            break;
                        }

                    }
                }
            }
            if (count > -1)
            {
                if (factorMap.get(count).size() < 4 || !checkFactors(factorMap, count))
                {
                    clearFactors(factorMap);
                    count = -1;
                }
            }
            if (count == 3)
            {
                System.out.println(i);
                break;
            }
        }
    }

    public static boolean checkFactors(final Map<Integer, List<Integer>> factorMap, final int count)
    {
        final boolean ret = true;
        for (final Integer factor : factorMap.get(count))
        {
            for (final Integer key : factorMap.keySet())
            {
                if (key.intValue() != count)
                {
                    if (factorMap.get(key).contains(factor))
                    {
                        return false;
                    }
                }
            }
        }
        return ret;
    }

    public static int adddfactor(final Map<Integer, List<Integer>> factorMap, final int count, final int factor,
            final int factorMultiply)
    {
        if (factorMap.get(count).contains(factor))
        {
            final int index = factorMap.get(count).indexOf(factor);
            factorMap.get(count).set(index, factor * factor);
            return factor * factor;
        }
        else
        {
            if (factorMap.get(count).contains(factorMultiply))
            {
                final int index = factorMap.get(count).indexOf(factorMultiply);
                factorMap.get(count).set(index, factorMultiply * factor);
                return factorMultiply * factor;
            }
            else
            {
                factorMap.get(count).add(factor);
                return factor;
            }

        }
    }

    public static void clearFactors(final Map<Integer, List<Integer>> factorMap)
    {
        for (final Integer key : factorMap.keySet())
        {
            factorMap.get(key).clear();
        }
    }

}
