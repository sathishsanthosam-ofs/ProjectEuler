package com.project.euler;

public class PermutedMultiples
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        for (int i = 1;; i++)
        {
            if (isSameDigits("" + i, "" + (2 * i)) && isSameDigits("" + i, "" + (3 * i))
                    && isSameDigits("" + i, "" + (4 * i)) && isSameDigits("" + i, "" + (5 * i))
                    && isSameDigits("" + i, "" + (6 * i)))
            {
                System.out.println(i);
                break;
            }
        }
    }

    public static boolean isSameDigits(final String a, String b)
    {

        for (int i = 0; i < a.length(); i++)
        {
            final char c = a.charAt(i);
            b = b.replace(c, '0');
        }
        return Integer.parseInt(b) == 0;
    }

}
