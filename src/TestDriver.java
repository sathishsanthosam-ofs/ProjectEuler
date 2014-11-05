import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HConstants;


public class TestDriver
{


    public TestDriver(final int a)
    {

    }


    public static void main(final String[] args)
    {
        final Configuration conf = new Configuration();
        conf.addResource(new Path("C:/cygwin/home/sathishkumars/hadoop-0.20.2/conf/core-site.xml"));
        conf.addResource(new Path("C:/cygwin/home/sathishkumars/hadoop-0.20.2/conf/hdfs-site.xml"));
        conf.addResource(new Path("/C:/cygwin/home/sathishkumars/hadoop-0.20.2/conf/mapred-site.xml"));

        final Configuration hbaseConf = new Configuration();
        hbaseConf.addResource(new Path("C:/cygwin/home/sathishkumars/hbase-0.94.0/conf/core-site.xml"));
        hbaseConf.addResource(new Path("C:/cygwin/home/sathishkumars/hbase-0.94.0/conf/hbase-site.xml"));
        hbaseConf.addResource(new Path("/C:/cygwin/home/sathishkumars/hbase-0.94.0/conf/hbase-default.xml"));
        final FileSystem fileSystem;
        try
        {
            final Path p = new Path(hbaseConf.get(HConstants.HBASE_DIR));
            final FileSystem fs = p.getFileSystem(hbaseConf);
            System.out.println(p.makeQualified(fs));

            // fileSystem = FileSystem.get(conf);
            final URI uri = fs.getUri();
            System.out.println(uri.getScheme());
            System.out.println(uri.getAuthority());
            final Path path = new Path("/In/README.txt");
            if (fs.exists(path))
            {
                System.out.println("File already exists");
                return;
            }
            else
            {
                System.out.println(path.toUri());
            }
        }
        catch (final IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
