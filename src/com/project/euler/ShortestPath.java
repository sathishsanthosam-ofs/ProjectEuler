package com.project.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPath
{
    public static void main(final String[] args)
    {
        final String[] test = { "5", "N1", "N2", "N3", "N4", "N5", "N1-N3", "N3-N4", "N4-N5", "N5-N2", "N2-N1" };
        final ShortestPath sp = new ShortestPath();
        System.out.println(sp.shortestPath(test));

    }

    private String recursepath(final String startElement, final String endElement,
            final Map<String, List<String>> pathMap, final int currentDepth, final String path,
            final List<String> crossedpath)
    {
        int depth = 0;
        String ret = null;
        final List<String> pathList = pathMap.get(startElement);
        if (pathList != null)
        {
            for (final String nextPath : pathMap.get(startElement))
            {
                if (nextPath.equals(endElement))
                {
                    return path + "-" + endElement;
                }
                else if (!crossedpath.contains(nextPath))
                {
                    crossedpath.add(nextPath);
                    final String childPath = recursepath(nextPath, endElement, pathMap, currentDepth + 1, path + "-"
                            + nextPath, crossedpath);
                    if (childPath != null)
                    {
                        if (depth == 0 || childPath.split("-").length < depth)
                        {
                            ret = childPath;
                            depth = childPath.split("-").length;
                        }
                    }
                    crossedpath.remove(nextPath);
                }
            }
        }

        return ret;
    }

    public String shortestPath(final String[] test)
    {
        final Map<String, List<String>> pathMap = new HashMap<String, List<String>>();
        final int size = Integer.parseInt(test[0]);
        final int totalSize = test.length;
        final String startElement = test[1];
        final String endElement = test[size];

        for (int i = size + 1; i < totalSize; i++)
        {
            final String[] link = test[i].split("-");
            final String start = link[0];
            final String end = link[1];
            List<String> paths = pathMap.get(start);
            if (paths == null)
            {
                paths = new ArrayList<String>();
                pathMap.put(start, paths);
            }
            paths.add(end);

            paths = pathMap.get(end);
            if (paths == null)
            {
                paths = new ArrayList<String>();
                pathMap.put(end, paths);
            }
            paths.add(start);
        }
        final List<String> crossedPath = new ArrayList<String>();
        crossedPath.add(startElement);
        final String ret = recursepath(startElement, endElement, pathMap, 0, startElement, crossedPath);
        return ret == null ? "-1" : ret;
    }
}
