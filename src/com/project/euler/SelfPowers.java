package com.project.euler;

import java.math.BigInteger;

public class SelfPowers
{
    public static void main(final String[] args)
    {
        BigInteger ret = new BigInteger("1");
        for (int i = 2; i < 1001; i++)
        {
            ret = ret.add(new BigInteger("" + i).pow(i));
        }
        System.out.println(ret.toString().substring(ret.toString().length() - 10));

    }
}
