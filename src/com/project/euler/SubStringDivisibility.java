package com.project.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubStringDivisibility
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<String> list = new ArrayList<String>();
        final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 5);
        map.put(5, 7);
        map.put(6, 11);
        map.put(7, 13);
        map.put(8, 17);
        swapValues("", "0123456789", list);
        BigInteger val = BigInteger.ZERO;
        for (final String string : list)
        {
            boolean ret = true;
            for (int j = 2; j < 9; j++)
            {
                if (!subStringDivision(j, string, map.get(j)))
                {
                    ret = false;
                    break;
                }
            }
            if (ret)
            {
                val = val.add(new BigInteger(string));
            }
        }
        System.out.println(val.toString());
    }

    private static boolean isPandigital(String s)
    {
        if (s.contains("0"))
        {
            return false;
        }
        for (int i = 1; i < 10; i++)
        {
            if (s.indexOf("" + i) != -1)
            {
                s = s.replaceFirst("" + i, "0");
            }
            else
            {
                return false;
            }
        }
        return Integer.parseInt(s) == 0;
    }

    private static boolean subStringDivision(final int i, final String s, final int divisior)
    {
        final String sub = s.substring(i - 1, i + 2);

        return Integer.parseInt(sub) % divisior == 0;
    }


    private static void swapValues(final String text, final String val, final List<String> ret)
    {
        if (val.length() == 2)
        {
            ret.add(text + val);
            ret.add(text + val.charAt(1) + val.charAt(0));
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
