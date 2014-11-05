package com.project.euler;

import java.util.ArrayList;
import java.util.List;

public class FindReciprocalCycle
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int max = 0;
        int ret = 0;
        for (int i = 2; i < 1000; i++)
        {
            final String s = getReciprocalCycle(i);
            if (s.length() > max)
            {
                max = s.length();
                System.out.println(i + "  Reciprocal Cycle  : " + max);
                ret = i;
            }
        }
        // System.out.println(ret);
    }

    private static String getReciprocalCycle(final int i)
    {
        final List<String> reminders = new ArrayList<String>();
        int reminder = (10 % i);
        String ret = "" + (10 / i);
        if (reminder == 0 || reminder == 1)
        {
            return ret;
        }

        while (!reminders.contains("" + reminder))
        {
            reminders.add("" + reminder);
            ret = ret + (Integer.parseInt(reminder + "0") / i);
            reminder = (Integer.parseInt(reminder + "0") % i);
        }
        return ret.substring(0, ret.length() - 1);
    }
}
