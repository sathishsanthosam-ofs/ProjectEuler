package com.project.euler;

public class SumSquareDiff
{

    int id = 10;

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        System.out.println(squareOfSum(100) - sumOfSquare(100));
    }

    public static int sumOfSquare(final int n)
    {
        return (n * (n + 1) * ((2 * n) + 1)) / 6;
    }

    public static int squareOfSum(final int n)
    {
        return ((n * (n + 1)) / 2) * ((n * (n + 1)) / 2);
    }
}
