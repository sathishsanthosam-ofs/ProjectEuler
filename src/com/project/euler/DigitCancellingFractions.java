package com.project.euler;


public class DigitCancellingFractions
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        double numartor = 1;
        double denominator = 1;
        for (double i = 10; i < 100; i++)
        {
            for (double j = 10; j < 100; j++)
            {
                if (i != j)
                {
                    if (isDigitCancellingFactor(i, j))
                    {
                        if ((i / j) < 1)
                        {
                            numartor = numartor * i;
                            denominator = denominator * j;
                            System.out.println(" numartor : " + i + " denominator : " + j);
                        }
                    }
                }
            }
        }
        System.out.println("Numarator " + numartor);
        System.out.println("denominator " + denominator);
    }

    private static boolean isDigitCancellingFactor(final double i, final double j)
    {
        StringBuffer numarator = new StringBuffer("" + i);
        StringBuffer denaminator = new StringBuffer("" + j);
        for (int k = 0; k < numarator.length(); k++)
        {
            final char c = numarator.charAt(k);
            if (c == '.')
            {
                break;
            }
            if (c != '0' && denaminator.indexOf("" + c) != -1)
            {
                numarator = numarator.deleteCharAt(k);
                denaminator = denaminator.deleteCharAt(denaminator.indexOf("" + c));
                final double newNumarator = Double.parseDouble(numarator.toString());
                final double newDenamitor = Double.parseDouble(denaminator.toString());
                if (newDenamitor > 0 && ((newNumarator / newDenamitor) == (i / j)))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

        return false;
    }
}
