package com.project.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MaximumPathSumIandII
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args)
            throws IOException
    {
        final BufferedReader br = new BufferedReader(new FileReader(new File(
                "C:/Documents and Settings/sathishkumars/My Documents/Downloads/triangle18.txt")));
        String temp = null;
        final int[][] grid = new int[100][100];
        int i = 0;
        while ((temp = br.readLine()) != null)
        {
            final String[] value = temp.split(" ");
            for (int j = 0; j < value.length; j++)
            {
                grid[i][j] = Integer.parseInt(value[j]);
            }
            i++;
        }
        for (int j = grid.length - 2; j >= 0; j--)
        {
            final int[] curRow = grid[j];
            for (int k = 0; k < curRow.length; k++)
            {
                final int curVal = grid[j][k];
                if (curVal != 0)
                {
                    final int firstVal = curVal + grid[j + 1][k];
                    final int secondVal = curVal + grid[j + 1][k + 1];
                    if (firstVal > secondVal)
                    {
                        grid[j][k] = firstVal;
                    }
                    else
                    {
                        grid[j][k] = secondVal;
                    }
                }

            }
        }
        System.out.println(grid[0][0]);
    }
}
