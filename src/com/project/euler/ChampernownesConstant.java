package com.project.euler;

public class ChampernownesConstant
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        StringBuffer s = new StringBuffer("0");
        for (int i = 1;; i++)
        {
            s = s.append(i);
            if (s.length() > 1000000)
            {
                break;
            }
        }
        System.out.println((Integer.parseInt("" + s.charAt(1)) * Integer.parseInt("" + s.charAt(10))
                * Integer.parseInt("" + s.charAt(100)) * Integer.parseInt("" + s.charAt(1000))
                * Integer.parseInt("" + s.charAt(10000)) * Integer.parseInt("" + s.charAt(100000)) * Integer.parseInt(""
                + s.charAt(1000000))));
    }
}
