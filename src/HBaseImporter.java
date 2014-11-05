// HBaseImporter.java - import data to an HBase table from a CSV formatted file 
// Copyright (c) 2011 Niall McCarroll  
// Distributed under the MIT/X11 License (http://www.mccarroll.net/snippets/license.txt)


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.log4j.Logger;

public class HBaseImporter extends HBaseImportExport
{

    HBaseAdmin admin;

    Configuration config;

    Set<String> families = new HashSet<String>();

    List<HBaseCol> columns = new ArrayList<HBaseCol>();

    String tableName;

    int keyPosition = -1;

    public HBaseImporter(final String tableName)
    {
        this.tableName = tableName;
    }

    public void init()
            throws MasterNotRunningException, ZooKeeperConnectionException
    {
        config = HBaseConfiguration.create();
        admin = new HBaseAdmin(config);
    }

    private void deleteTable()
    {
        try
        {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }
        catch (final Exception e)
        {
        }
    }

    private void createTable()
            throws IOException
    {
        final HTableDescriptor desc = new HTableDescriptor(tableName);
        admin.createTable(desc);
        admin.disableTable(tableName);
        for (final String family : families)
        {
            final HColumnDescriptor cf1 = new HColumnDescriptor(family);
            admin.addColumn(tableName, cf1);
        }
        admin.enableTable(tableName);
    }

    private void analyzeHeaders(final String[] headers, final String keyColumn)
    {
        columns.clear();
        families.clear();
        int col = 0;
        for (final String header : headers)
        {
            String family = DEFAULT_COLUMN_FAMILY;
            String qualifier = header;
            int pos;
            if ((pos = header.indexOf(":")) > 0)
            {
                family = header.substring(0, pos);
                qualifier = header.substring(pos + 1);
            }
            columns.add(new HBaseCol(family, qualifier));
            families.add(family);
            if (header.equals(keyColumn))
            {
                keyPosition = col;
            }
            col++;
        }
    }

    private void loadData(final CsvInputStream cis)
            throws IOException
    {

        final HTable table = new HTable(config, tableName);

        String vals[] = cis.readLine();

        final Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
        int counter = 0;
        String rowId = "";
        while (vals != null)
        {
            if (keyPosition >= 0 && keyPosition < vals.length)
            {
                rowId = vals[keyPosition];
            }
            else
            {
                rowId = "r" + counter;
            }
            final Put put = new Put(rowId.getBytes("UTF-8"));

            int col = 0;
            for (final HBaseCol column : columns)
            {
                if (col >= vals.length)
                {
                    break;
                }
                put.add(column.family.getBytes("UTF-8"), column.qualifier.getBytes(), vals[col].getBytes());
                col += 1;
            }
            table.put(put);
            vals = cis.readLine();
            counter += 1;
            if (counter % 10000 == 0)
            {
                logger.info("Imported " + counter + " records");
            }
        }
        cis.close();
    }

    /**
     * import CSV to an HBase table
     * 
     * @param tableName name of the table in HBase
     * @param csvFile a file
     * 
     * @throws IOException
     */
    public void importCSV(final File csvFile, final String keyColumn)
            throws IOException
    {
        init();

        final FileInputStream fis = new FileInputStream(csvFile);
        final CsvInputStream cis = new CsvInputStream(fis);

        // read field names from the first line of the csv file
        analyzeHeaders(cis.readLine(), keyColumn);

        deleteTable();
        createTable();
        loadData(cis);
        cis.close();
    }

    public static void main(final String[] args)
            throws IOException
    {
        if (args.length < 2 || args.length > 3)
        {
            System.out.println("Usage: HBaseImporter <tablename> <csv file path> [<key field name>]");
        }

        final String tableName = args[0];
        final File f = new File(args[1]);
        String keyColumn = null;
        if (args.length > 2)
        {
            keyColumn = args[2];
        }
        final HBaseImporter importer = new HBaseImporter(tableName);
        importer.importCSV(f, keyColumn);
    }

}