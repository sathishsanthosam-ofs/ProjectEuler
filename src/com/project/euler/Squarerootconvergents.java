package com.project.euler;


public class Squarerootconvergents
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int count = 0;
        BigFraction previous = BigFraction.ZERO;
        System.out.println(previous);
        for (int i = 0; i < 8; i++)
        {
            final BigFraction current = BigFraction.ONE.divide(previous.add(BigFraction.TWO));
            final BigFraction val = BigFraction.ONE.add(current);
            System.out.println(val);
            previous = current;
            if (("" + val.getNumerator()).length() > ("" + val.getDenominator()).length())
            {
                count++;
            }
        }
        System.out.println(count);
    }
}
