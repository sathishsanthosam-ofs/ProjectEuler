package com.project.euler;

import java.util.Arrays;
import java.util.List;

public class CountingSundays
{
    public static int YEAR = 1901;

    public static int MONTH = 1;

    public static List<Integer> longMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);

    public static List<Integer> shortMonths = Arrays.asList(4, 6, 9, 11);


    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int ret = 0;
        int day = 5;
        while (YEAR < 2001)
        {
            day = day + 7;
            if (day >= 28)
            {
                boolean isChanged = false;
                if (longMonths.contains(MONTH) && day >= 31)
                {
                    day = day - 31;
                    isChanged = true;
                }
                else if (shortMonths.contains(MONTH) && day >= 30)
                {
                    day = day - 30;
                    isChanged = true;
                }
                else if (MONTH == 2)
                {
                    final boolean isLeap = ((YEAR % 4) == 0) ? true : false;
                    day = isLeap ? day - 29 : day - 28;
                    isChanged = true;
                }
                if (isChanged)
                {
                    YEAR = (MONTH == 12) ? (YEAR + 1) : YEAR;
                    MONTH = (MONTH == 12) ? 1 : (MONTH + 1);
                }


            }
            if (day == 1)
            {
                ret++;
            }
        }
        System.out.println(day);
        System.out.println(ret);
    }

}
