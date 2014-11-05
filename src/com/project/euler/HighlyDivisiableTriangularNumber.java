package com.project.euler;

public class HighlyDivisiableTriangularNumber
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {

        boolean isReached = false;
        long ret = 0;
        for (int i = 0;; i++)
        {
            ret = ((i * (i + 1)) / 2);
            int count = 0;
            final long max = (long) Math.sqrt(ret);
            for (int j = 1; j <= max; j++)
            {
                if ((ret % j) == 0)
                {
                    count = count + 2;
                }
            }
            System.out.println(" Triangle no " + i + " Count " + count);
            isReached = (count >= 500);
            if (isReached)
            {
                break;
            }
        }
        System.out.println(ret);

    }

}
