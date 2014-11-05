package com.project.euler;
public class MultiplesOf3And5
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        System.out.println(sumDivisibleBy(999, 3) + sumDivisibleBy(999, 5) - sumDivisibleBy(999, 15));

    }

    public static int sumDivisibleBy(final int sum, final int divisible)
    {
        final int last = (sum / divisible);
        final float total = (last + 1) / 2f;
        final int ret = (int) (divisible * (last * total));
        return ret;
    }

}
