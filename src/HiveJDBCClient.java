import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HiveJDBCClient
{
    private static String driverName = "oracle.jdbc.driver.OracleDriver";

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(final String[] args)
            throws SQLException
    {
        final Driver driver = null;
        // TODO Auto-generated method stub
        try
        {
            Class.forName(driverName);
            // driver = DriverLoader.load(driverName);
        }
        catch (final ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        final Properties props = new Properties();
        props.put("user", "system");
        props.put("password", "manager");

        // final Connection con =
        // DriverManager.getConnection("jdbc:hive://hadoop1.liaisondevqa.local:10000/default",props);
        final Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:ofs", "system",
                "manager");
        final Statement stmt = con.createStatement();

        final String tableName = "SathishHive";

        String sql = "create table "
                + tableName
                + " (f1 String, f2 int) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES (\"hbase.columns.mapping\" = \":key,cf1:val\")"
                + "TBLPROPERTIES (\"hbase.table.name\" = \"SathishHBase\")";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);

        // show tables
        sql = "show tables '" + tableName + "'";
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        if (res.next())
        {
            System.out.println(res.getString(1));
        }
        // describe table
        sql = "describe " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next())
        {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }
        // load data into table

        sql = "select * from SathishHive";
        res = stmt.executeQuery(sql);
        while (res.next())
        {
            System.out.println(res.getString(1) + "\t" + res.getInt(2));
        }

    }

    public static Connection connectDb()
    {
        Connection con = null;
        try
        {
            /*
             * Instead of solely using drivers provided with the product, we will create a directory where drivers may
             * be added by the user. The Bottomline JDBC driver provides an interface which will allow Builder to
             * dynamically load these drivers.
             * 
             * pwright
             */
            // JDBC Bridge
            HiveJDBCClient.loadClass("xeus.bottomline.BottomlineDriver");

            final Properties properties = new Properties();
            properties.put("user", "");
            properties.put("password", "");
            properties.put("jar", "D:\\dev\\workspace\\pharma3.6.1\\Test-Mohan\\lib\\hive-jdbc-0.7.1-cdh3u4.jar");

            properties.put("class", driverName);
            String serverString = "jdbc:hive://hadoop1.liaisondevqa.local:10000/default";
            if (serverString.startsWith("jdbc:"))
            {
                serverString = serverString.replaceFirst("jdbc:", "");
            }
            con = DriverManager.getConnection("jdbc:bottomline:" + serverString, properties);
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return con;
    }

    private static Class loadClass(final String classFileName)
    {
        try
        {
            // return Class.forName(classFileName, true,
            // Thread.currentThread().getContextClassLoader());

            // We should probably load this from some default directory to allow
            // for the ability of the user to drop in drivers after installation

            return Class.forName(classFileName);
        }
        catch (final ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException("failure.persistence.ClassNotFound: " + classFileName + " -> " + e.getMessage());
        }
    }

}