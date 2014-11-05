package com.project.euler;

import java.util.HashSet;
import java.util.Set;

public class PandigitalProducts
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final Set<Integer> set = new HashSet<Integer>();
        int ret = 0;
        for (int i = 1; i < (987654322 / 2); i++)
        {
            for (int j = 1;; j++)
            {
                final String s = "" + i + j + (i * j);
                if (s.length() > 9)
                {
                    break;
                }
                else if (s.length() == 9 && isPandigital(s))
                {
                    if (set.add(i * j))
                    {
                        ret = ret + (i * j);
                    }
                }
            }
        }
        System.out.println(ret);
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
}
