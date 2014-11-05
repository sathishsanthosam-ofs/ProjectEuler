package com.project.euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimePermutations
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<Integer> all = new ArrayList<Integer>();
        for (int i = 1001; i < 9999; i++)
        {
            if (TruncatablePrimes.isPrime(i))
            {
                final List<Integer> ret = new ArrayList<Integer>();
                swapValues("", "" + i, ret);
                Collections.sort(ret);
                final List<Integer> diffList = new ArrayList<Integer>();
                for (int j = 0; j < ret.size(); j++)
                {
                    final int diff = ret.get(j) - i;
                    if (diff > 0)
                    {
                        diffList.add(diff);
                        if (diffList.contains(diff / 2) && (i != 1487))
                        {
                            System.out.println("" + i + "" + (i + (diff / 2)) + "" + ret.get(j));
                            break;
                        }
                    }
                }

            }
        }
    }

    private static void swapValues(final String text, final String val, final List<Integer> ret)
    {
        if (val.length() == 2)
        {
            if (TruncatablePrimes.isPrime(Integer.parseInt(text + val)))
            {
                ret.add(Integer.parseInt(text + val));
            }

            if (TruncatablePrimes.isPrime(Integer.parseInt(text + val.charAt(1) + val.charAt(0))))
            {
                ret.add(Integer.parseInt(text + val.charAt(1) + val.charAt(0)));
            }

        }
        else
        {
            for (int i = 0; i < val.length(); i++)
            {
                swapValues(text + val.charAt(i), val.substring(i + 1) + val.substring(0, i), ret);
            }
        }
    }

}
