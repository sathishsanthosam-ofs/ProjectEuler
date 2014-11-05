package com.project.euler;

public class PandigitalMultiples
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        System.out.println("192384576".length());
        System.out.println(isPandigital("5207801040"));
        int ret = 0;
        for (int i = 1; i < (987654322 / 2); i++)
        {
            String s = "";
            for (int j = 1; j < 10; j++)
            {
                s = s + (i * j);
                if (s.length() > 9)
                {
                    break;
                }
                else if (j > 1 && s.length() == 9 && isPandigital(s))
                {
                    if (Integer.parseInt(s) > ret)
                    {
                        System.out.println("Pandigital No : " + ret + " concatenated Product of " + i + " [" + j + "]");
                        ret = Integer.parseInt(s);
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
