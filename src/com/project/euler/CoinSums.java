package com.project.euler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CoinSums
{


    /**
     * @param args
     * @throws Throwable
     */
    public static void main(final String[] args)
            throws Throwable
    {
        final CoinSums s = new CoinSums();
        final List<Integer> coins = new ArrayList<Integer>();
        coins.add(200);
        coins.add(100);
        coins.add(50);
        coins.add(20);
        coins.add(10);
        coins.add(5);
        coins.add(2);
        coins.add(1);

        // final Map<Integer, Set<String>> coinMap = new HashMap<Integer, Set<String>>();
        // final Set<String> one = new HashSet<String>();
        // one.add("001*1P");
        // coinMap.put(1, one);
        // final Set<String> two = new HashSet<String>();
        // two.add("002*1P");
        // two.add("001*2P");
        // coinMap.put(2, two);
        // for (int i = 3; i < 11; i++)
        // {
        // final Set<String> temp = new HashSet<String>();
        // temp.add(i + "*1P");
        // if (coins.contains(i))
        // {
        // temp.add("1*" + i + "P");
        // }
        // for (int j = 1; j < (i / 2) + 1; j++)
        // {
        // final Set<String> first = coinMap.get(j);
        // final Set<String> second = coinMap.get(i - j);
        // for (final String firstString : first)
        // {
        // for (final String secndString : second)
        // {
        // temp.add(combined(firstString, secndString, coins, i));
        // }
        // }
        // }
        // coinMap.put(i, temp);
        // }
        // System.out.println(coinMap.get(10));
        // System.out.println(coinMap.get(10).size());
        final Set<String> ret = new HashSet<String>();
        getCombinations(0, "", ret, coins, 200);
        System.out.println(ret.size());
    }

    public static String combined(final String first, final String second, final List<Integer> coins, final int value)
    {
        StringBuffer ret = new StringBuffer();
        for (final Integer integer : coins)
        {
            if (integer.intValue() <= value)
            {
                int count = 0;
                if (second.contains(integer.intValue() + "P"))
                {
                    String temp = second.substring(0, second.indexOf("*" + integer.intValue() + "P"));
                    temp = temp.substring(temp.lastIndexOf(",") + 1, temp.length());
                    count = count + Integer.parseInt(temp);
                }
                if (first.contains(integer.intValue() + "P"))
                {
                    String temp = first.substring(0, first.indexOf("*" + integer.intValue() + "P"));
                    temp = temp.substring(temp.lastIndexOf(",") + 1, temp.length());
                    count = count + Integer.parseInt(temp);
                }
                if (count > 0)
                {
                    ret = ret.append(count + "*" + integer.intValue() + "P,");
                }
            }
            else
            {
                break;
            }

        }

        return ret.substring(0, ret.length() - 1);
    }

    public static void getCombinations(final int index, final String text, final Set<String> ret,
            final List<Integer> coins, final int value)
    {
        for (int i = index; i < coins.size(); i++)
        {
            final int coin = coins.get(i);
            final int quotient = value / coin;
            final int remainder = value % coin;
            if (remainder == 0)
            {
                ret.add(text + " + " + quotient + " * " + coin);
                if (quotient > 1 && coin != 1)
                {
                    for (int j = quotient - 1; j >= 0; j--)
                    {
                        if (j == 0)
                        {
                            getCombinations(i + 1, text, ret, coins, value - ((j) * coin));
                        }
                        else
                        {
                            getCombinations(i + 1, text + " + " + (j) + " * " + coin, ret, coins, value - ((j) * coin));
                        }
                    }
                }
            }
            else
            {
                if (quotient > 0)
                {
                    getCombinations(i + 1, text + " + " + (quotient) + " * " + coin, ret, coins, remainder);
                    if (quotient > 1)
                    {
                        for (int j = quotient - 1; j >= 0; j--)
                        {
                            if (j == 0)
                            {
                                getCombinations(i + 1, text, ret, coins, value - ((j) * coin));
                            }
                            else
                            {
                                getCombinations(i + 1, text + " + " + (j) + " * " + coin, ret, coins, value
                                        - ((j) * coin));
                            }
                        }
                    }
                }

            }
        }
    }
}
