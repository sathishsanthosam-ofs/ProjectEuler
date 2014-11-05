package com.project.euler;


public class LatticePaths
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        // //
        // final int grid = 21;
        // long[][] horizondalgrid = new long[grid][grid];
        // long[][] verticalGrid = new long[grid][grid];
        // // Intial Grid
        // for (int i = 0; i < 1; i++)
        // {
        // for (int j = 1; j < horizondalgrid[i].length; j++)
        // {
        // if (j == horizondalgrid[i].length - 1)
        // {
        // horizondalgrid[i][j] = 1;
        // }
        // else
        // {
        // horizondalgrid[i][j] = horizondalgrid[i].length - 1;
        // }
        //
        // }
        // }
        // for (int i = 1; i < verticalGrid.length; i++)
        // {
        // for (int j = 0; j < 1; j++)
        // {
        // if (i == verticalGrid.length - 1)
        // {
        // verticalGrid[i][j] = 1;
        // }
        // else
        // {
        // verticalGrid[i][j] = verticalGrid[i].length - 1;
        // }
        // }
        // }
        // while (isFinished(horizondalgrid) || isFinished(verticalGrid))
        // {
        // final long[][] temphorizondalgrid = new long[grid][grid];
        // final long[][] tempverticalGrid = new long[grid][grid];
        // for (int i = 0; i < verticalGrid.length; i++)
        // {
        // for (int j = 0; j < verticalGrid[i].length; j++)
        // {
        // if (verticalGrid[i][j] > 0)
        // {
        // if (i == verticalGrid.length - 1)
        // {
        // if (j != verticalGrid.length - 1)
        // {
        // final long val = horizondalgrid[i][i] + verticalGrid[i][j];
        // horizondalgrid[i][i] = val;
        // temphorizondalgrid[i][i] = val;
        // }
        //
        // }
        // else
        // {
        // final long value = verticalGrid[i][j];
        // long iteration = 1;
        // if (value > verticalGrid.length - (1 + j))
        // {
        // iteration = value / (verticalGrid.length - (1 + j));
        // }
        // for (int z = 1; z < verticalGrid.length - j; z++)
        // {
        // final int index = (j + z);
        // long val = temphorizondalgrid[i][index];
        // if ((j + z) >= verticalGrid.length - 1)
        // {
        // val = val + iteration;
        // }
        // else
        // {
        // val = val + ((verticalGrid.length - (i + 1)) * iteration);
        // }
        // temphorizondalgrid[i][index] = val;
        // }
        //
        // }
        // if (!(i == verticalGrid.length - 1 && j == verticalGrid.length - 1))
        // {
        // verticalGrid[i][j] = 0;
        // }
        //
        // }
        // }
        // }
        //
        // for (int i = 0; i < horizondalgrid.length; i++)
        // {
        // for (int j = 0; j < horizondalgrid[i].length; j++)
        // {
        // if (horizondalgrid[i][j] > 0)
        // {
        // if (j == horizondalgrid.length - 1)
        // {
        // if (i != horizondalgrid.length - 1)
        // {
        // final long val = verticalGrid[j][j] + horizondalgrid[i][j];
        // verticalGrid[j][j] = val;
        // tempverticalGrid[j][j] = val;
        // }
        //
        // }
        // else
        // {
        // final long value = horizondalgrid[i][j];
        // long iteration = 1;
        // if (value > horizondalgrid.length - (1 + i))
        // {
        // iteration = value / (horizondalgrid.length - (1 + i));
        // }
        // for (int z = 1; z < horizondalgrid.length - i; z++)
        // {
        // final int index = (i + z);
        // long val = tempverticalGrid[index][j];
        // if ((i + z) >= verticalGrid.length - 1)
        // {
        // val = val + iteration;
        // }
        // else
        // {
        // val = val + ((verticalGrid[i + z].length - (j + 1)) * iteration);
        // }
        // tempverticalGrid[index][j] = val;
        // }
        //
        // }
        // if (!(i == horizondalgrid.length - 1 && j == horizondalgrid.length - 1))
        // {
        // horizondalgrid[i][j] = 0;
        // }
        //
        // }
        // }
        // }
        // horizondalgrid = temphorizondalgrid;
        // verticalGrid = tempverticalGrid;
        // }
        // System.out.println(horizondalgrid[grid - 1][grid - 1] + verticalGrid[grid - 1][grid - 1]);
        // System.out.println(lpath(21, 21));
        final long[][] a = new long[21][21];

        int i, j;

        for (i = 1; i < 21; i++)
        {
            a[i][0] = 1;
        }

        for (j = 1; j < 21; j++)
        {
            a[j][j] = a[j][j - 1] * (2);

            for (i = j + 1; i < 21; i++)
            {
                a[i][j] = a[i - 1][j] + (a[i][j - 1]);
            }
        }


        System.out.println(a[20][20]);
    }

    private static boolean isFinished(final long[][] grid)
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j] > 0)
                {
                    if (!(i == (grid.length - 1) && j == (grid[i].length - 1)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
