import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;

public class DriverLoader extends URLClassLoader
{
    private static DriverLoader driverLoader;

    // static
    // {
    // final URL urls[] = {};
    // driverLoader = new DriverLoader(urls);
    // // DriverManager.registerDriver(driverLoader);
    // }

    private DriverLoader(final URL[] urls)
    {
        super(urls);
        final File driverFolder = new File("C:\\Contivo\\Contivo_Analyst_5_0_1\\DriverJar");
        final File[] files = driverFolder.listFiles();
        for (final File file : files)
        {
            try
            {
                addURL(file.toURI().toURL());
            }
            catch (final MalformedURLException e)
            {
            }
        }
    }


    public static Driver load(final String driverClassName)
            throws ClassNotFoundException
    {
        try
        {
            Class.forName(driverClassName);
        }
        catch (final ClassNotFoundException ex)
        {
            if (driverLoader == null)
            {
                final URL urls[] = {};
                driverLoader = new DriverLoader(urls);
            }
            final Class driverClass = driverLoader.loadClass(driverClassName);
            try
            {
                return (Driver) driverClass.newInstance();
            }
            catch (final InstantiationException e)
            {

            }
            catch (final IllegalAccessException e)
            {

            }

        }
        return null;
    }
}