package com.project.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GoldbachsOtherConjecture
{
    final public static BigInteger two = new BigInteger("2");

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final List<Integer> prime = new ArrayList<Integer>();
        prime.add(1);
        prime.add(2);
        prime.add(3);
        prime.add(5);
        prime.add(7);
        boolean found = true;
        for (int i = 11;; i = i + 2)
        {
            if (TruncatablePrimes.isPrime(i))
            {
                prime.add(i);
            }
            else
            {
                found = false;
                for (final Integer integer : prime)
                {
                    final int reminder = (i - integer);
                    if (reminder == 2)
                    {
                        found = true;
                        break;
                    }
                    else if (reminder != 1 && reminder != 0 && reminder % 2 == 0)
                    {
                        // System.out.println("The Number : " + i);
                        // System.out.println("The Prime : " + prime.get(j));
                        // System.out.println("The Reminder : " + reminder);
                        final BigInteger sqrt = TriPenHex.bigIntSqRootCeil(new BigInteger("" + (reminder / 2)));
                        if (sqrt.multiply(sqrt).multiply(two).toString().equals("" + reminder))
                        {
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (!found)
            {
                System.out.println(i);
                break;
            }

        }
    }
}
