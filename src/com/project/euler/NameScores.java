package com.project.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class NameScores
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args)
            throws IOException
    {
        final BufferedReader br = new BufferedReader(new FileReader(new File(
                "C:/Documents and Settings/sathishkumars/My Documents/Downloads/names.txt")));
        String temp = null;
        String[] names = null;
        while ((temp = br.readLine()) != null)
        {
            names = temp.split(",");
            Arrays.sort(names);
        }
        long ret = 0;
        for (int i = 0; i < names.length; i++)
        {
            final String name = names[i];
            int val = 0;
            for (int j = 1; j < name.length() - 1; j++)
            {
                val = val + (name.charAt(j) - 64);
            }
            ret = ret + ((i + 1) * val);
        }
        System.out.println(ret);
    }

}
