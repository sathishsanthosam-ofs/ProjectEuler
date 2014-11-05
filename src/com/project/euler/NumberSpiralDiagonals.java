package com.project.euler;

public class NumberSpiralDiagonals
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        int ret = 1;
        int r = 2;
        int dia = 3;
        int curentValue = 1;
        while (curentValue < 1002001)
        {
            while (curentValue != (dia * dia))
            {
                curentValue = curentValue + r;
                ret = ret + curentValue;
            }
            r = r + 2;
            dia = dia + 2;
        }
        System.out.println(ret);
    }

}
