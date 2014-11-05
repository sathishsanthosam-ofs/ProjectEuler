package com.project.euler;

import java.util.HashSet;
import java.util.Set;


public class PythogoreanTriplet
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int max = 0;
        int ret = 0;
        final Set<String> solutions = new HashSet<String>();
        for (int j = 840; j < 1001; j++)
        {
            for (int i = 0; i < 180; i++)
            {
                final double radient = Math.toRadians(i);
                final int c = (int) Math.round((j / (1 + (Math.sin(radient)) + (Math.cos(radient)))));
                final int a = (int) Math.round((Math.sin(radient) * c));
                final int b = (int) Math.round((Math.cos(radient) * c));
                if ((a + b + c == j) && (((a * a) + (b * b) - (c * c)) == 0) && (a < b) && (b < c) && a > 0)
                {
                    solutions.add((a + "," + b + "," + c));
                }
            }
            if (solutions.size() > max)
            {
                System.out.println(j + " No of Solutions : " + solutions);
                max = solutions.size();
                ret = j;
            }
            // else if (solutions.size() == max)
            // {
            // System.out.println(j + " No of Solutions : " + solutions.size());
            // }
            solutions.clear();
        }
        System.out.println(ret + " No of Solutions : " + max);
        // final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // for (int i = 1; i < 1001; i++)
        // {
        // for (int j = i + 1; j < 1001; j++)
        // {
        // for (int k = j + 1; k < 1001; k++)
        // {
        // if ((i + j + k) > 1000)
        // {
        // break;
        // }
        // if (((i * i) + (j * j)) == (k * k))
        // {
        // if ((i + j + k) == 840)
        // {
        // System.out.println(i + "," + j + "," + k);
        // }
        // if (map.get((i + j + k)) != null)
        // {
        // map.put((i + j + k), map.get((i + j + k)) + 1);
        // }
        // else
        // {
        // map.put((i + j + k), 1);
        // }
        // }
        // }
        // }
        // }
        // final int ret = 0;
        // final int max = 0;
        // for (final Integer no : map.keySet())
        // {
        // if (map.get(no) > max)
        // {
        // max = map.get(no);
        // ret = no;
        // }
        // System.out.println(no + " No of Solutions : " + map.get(no));
        // }
        // System.out.println(ret + " No of Solutions : " + max);
    }
}
