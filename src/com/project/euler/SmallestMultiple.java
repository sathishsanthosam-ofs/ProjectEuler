package com.project.euler;

public class SmallestMultiple
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        long ret = 1;
        boolean isreached = false;
        System.out.println((1 % 20));
        while (!isreached)
        {
            int count = 0;
            for (int i = 20; i > 0; i--)
            {
                if ((ret % i) != 0)
                {
                    ret = ret + (i - (ret % i));
                    break;
                }
                else
                {
                    count++;
                }
            }
            isreached = (count == 20);
        }

        System.out.println(ret);
    }

}
