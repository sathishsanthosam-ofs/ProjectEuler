package com.project.euler;

import java.math.BigInteger;

public class Powerfuldigitcounts
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int count = 0;
        for (int i = 1; i < 100; i++)
        {
            for (int j = 1; j < 100; j++)
            {
                final BigInteger ret = new BigInteger("" + j).pow(i);
                if (ret.toString().length() == i)
                {
                    count++;
                }
                else if (ret.toString().length() > i)
                {
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
