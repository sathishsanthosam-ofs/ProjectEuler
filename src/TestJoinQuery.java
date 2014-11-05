import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;


public class TestJoinQuery
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        Connection connection;
        Driver driver;
        try
        {
            driver = DriverLoader.load("oracle.jdbc.driver.OracleDriver");
            final Properties props = new Properties();
            props.put("user", "CDATest");
            props.put("password", "manager");
            connection = driver.connect("jdbc:oracle:thin:@192.168.0.6:1521:ofs", props);
            final String sql = "select a.*,b.* from departments a, employees b where a.department_id = b.department_id";
            final PreparedStatement statement = connection.prepareStatement(sql);
            if (statement.execute())
            {
                final ResultSet rs = statement.getResultSet();

            }

        }
        catch (final Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
