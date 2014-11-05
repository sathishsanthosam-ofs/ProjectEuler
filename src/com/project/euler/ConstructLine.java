package com.project.euler;

public class ConstructLine
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        System.out.println(canConstruct(11, 8, 100));
    }

    public static boolean canConstruct(final int x, final int y, final int length)
    {
        int max = 0;
        boolean isX = false;
        if (x < y)
        {
            max = length / y;
            isX = true;
        }
        else
        {
            max = length / x;
        }
        for (int i = 0; i <= max + 1; i++)
        {
            if (isX)
            {
                final int a = (length - (y * i)) / x;

                if (((a * x) + (i * y)) == length)
                {
                    return true;
                }
            }
            else
            {
                final int b = (length - (x * i)) / y;

                if (((i * x) + (b * y)) == length)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
