package com.project.euler;

import java.util.ArrayList;
import java.util.List;

public class LexicographicPermutations
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<String> ret = new ArrayList<String>();
        final StringBuilder s = new StringBuilder();
        final StringBuilder s1 = new StringBuilder("0123456789");
        permutations(s1, s, ret);
        System.out.println(ret.get(999999));
    }

    private static void permutations(final StringBuilder valToChange, final StringBuilder valToAdd,
            final List<String> permutationList)
    {
        if (valToChange.length() == 2)
        {
            permutationList.add(valToAdd.toString() + valToChange.toString());
            permutationList.add(valToAdd.toString() + valToChange.reverse().toString());
        }
        else
        {
            for (int i = 0; i < valToChange.length(); i++)
            {
                StringBuilder current = null;
                StringBuilder previous = null;
                if (i == 0)
                {
                    current = new StringBuilder(valToChange.substring(i + 1));
                    previous = new StringBuilder(valToAdd.toString() + valToChange.charAt(i));
                    permutations(current, previous, permutationList);
                }
                else
                {
                    current = new StringBuilder(valToChange.substring(0, i) + valToChange.substring(i + 1));
                    previous = new StringBuilder(valToAdd.toString() + valToChange.charAt(i));
                    permutations(current, previous, permutationList);
                }
            }
        }
    }

}
