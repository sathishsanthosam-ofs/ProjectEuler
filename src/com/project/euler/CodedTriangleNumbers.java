package com.project.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CodedTriangleNumbers
{

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args)
            throws Exception
    {
        final BufferedReader br = new BufferedReader(new FileReader(new File(
                "C:/Documents and Settings/sathishkumars/My Documents/Downloads/words.txt")));
        String temp = null;
        String[] names = null;
        while ((temp = br.readLine()) != null)
        {
            names = temp.split(",");
            Arrays.sort(names);
        }
        final Map<Integer, Integer> wordsMap = new HashMap<Integer, Integer>();
        int max = 0;
        for (int i = 0; i < names.length; i++)
        {
            final String name = names[i];
            int val = 0;
            for (int j = 1; j < name.length() - 1; j++)
            {
                val = val + (name.charAt(j) - 64);
            }
            int count = 1;
            if (wordsMap.get(val) != null)
            {
                count = wordsMap.get(val) + 1;
            }
            wordsMap.put(val, count);
            if (val > max)
            {
                max = val;
            }
        }
        int ret = 0;
        for (int i = 1;; i++)
        {
            final int val = ((i * (i + 1)) / 2);
            if (wordsMap.keySet().contains(val))
            {
                ret = ret + wordsMap.get(val);
            }
            if (val > max)
            {
                break;
            }
        }
        System.out.println(ret);
    }

}
