package com.project.euler;

public class ConsecutivePrimeSum
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int consecutive = 0;
        int sum = 0;
        int maxSum = 0;
        int maxConsecutive = 0;
        for (int i = 2; i < 100; i++)
        {
            if (isPrime(i))
            {
                sum = sum + i;
                if (isPrime(sum))
                {
                    consecutive++;
                }
                else
                {
                    if (maxConsecutive < consecutive)
                    {
                        maxConsecutive = consecutive;
                        consecutive = 0;
                    }
                    if (maxSum < sum)
                    {
                        maxSum = sum;
                        sum = 0;
                    }
                }
            }
        }
        System.out.println("Max Sum : " + maxSum);
        System.out.println("Max Consecutive : " + maxConsecutive);
    }

    private static boolean isPrime(int n)
    {
        if (n < 0)
        {
            n = (-1 * n);
        }
        final int max = (int) Math.sqrt(n);
        for (int i = 2; i <= max; i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

}
