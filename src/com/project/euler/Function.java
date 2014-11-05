package com.project.euler;

import java.util.ArrayList;
import java.util.Scanner;

class Function
{
    String ShortestPath(final String[] strArr)
    {
        final int Nnodos = Integer.parseInt(strArr[0]);
        final ArrayList<String> nodos = new ArrayList<String>();
        final ArrayList<String> paths = new ArrayList<String>();

        for (int i = 1; i < Nnodos + 1; i++)
        {
            nodos.add(strArr[i]);
        }

        for (int i = Nnodos + 1; i < strArr.length; i++)
        {
            paths.add(strArr[i]);
            final String rotate[] = strArr[i].split("-");
            final String rot = rotate[1] + "-" + rotate[0];
            paths.add(rot);
        }

        final ArrayList<String> ways = new ArrayList<String>();
        ways.add(nodos.get(0));

        final ArrayList<String> temporal = new ArrayList<String>();
        temporal.add(nodos.get(0));

        for (int i = 0; i < ways.size(); i++)
        {
            final String path[] = ways.get(i).split("-");
            final String tempPath = path[path.length - 1];
            for (int j = 0; j < paths.size(); j++)
            {
                final String path1[] = paths.get(j).split("-");
                final String tempPath1AB = path1[0];
                final String tempPath1BA = path1[1];

                if (tempPath.equals(tempPath1AB))
                {
                    final String temp = ways.get(i) + "-" + path1[path1.length - 1];

                    final String control[] = temp.split("-");
                    if (!control[0].equals(control[control.length - 1]))
                    {
                        ways.add(temp);
                        for (int a = 0; a < ways.size() - 1; a++)
                        {
                            for (int b = a + 1; b < ways.size(); b++)
                            {
                                final String check1[] = ways.get(a).split("-");
                                final String check2[] = ways.get(b).split("-");
                                if (check1[0].equals(check2[0])
                                        && check1[check1.length - 1].equals(check2[check2.length - 1]))
                                {
                                    if (check1.length <= check2.length)
                                    {
                                        ways.remove(b);
                                    }
                                    else if (check1.length > check2.length)
                                    {
                                        ways.remove(a);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        final ArrayList<String> nodosFinal = new ArrayList<String>();
        for (int i = 0; i < ways.size(); i++)
        {
            if (ways.get(i).contains(nodos.get(0)) && ways.get(i).contains(nodos.get(nodos.size() - 1)))
            {
                if (nodosFinal.isEmpty())
                {
                    nodosFinal.add(ways.get(i));
                }
                else
                {
                    if (nodosFinal.get(0).length() > ways.get(i).length())
                    {
                        nodosFinal.remove(0);
                        nodosFinal.add(ways.get(i));
                    }
                }
            }
        }

        if (nodosFinal.isEmpty())
        {
            return "-1";
        }
        else
        {
            return nodosFinal.get(0);
        }
    }

    public static void main(final String[] args)
    {
        // keep this function call here
        final Scanner s = new Scanner(System.in);
        final Function c = new Function();
        final String[] test = { "5", "N1", "N2", "N3", "N4", "N5", "N1-N3", "N3-N4", "N4-N5", "N5-N2", "N2-N1" };
        System.out.print(c.ShortestPath(test));
    }

}