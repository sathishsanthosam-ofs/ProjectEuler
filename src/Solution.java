import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args)
            throws IOException
    {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] line = br.readLine().split(" ");
        final long n = Long.parseLong(line[0]);
        final long k = Long.parseLong(line[1]);
        final String[] values = br.readLine().split(" ");
        Arrays.sort(values);
        int count = 0;
        for (int i = 0; i < values.length; i++)
        {
            final long value1 = Long.parseLong(values[i]);

            for (int j = (i + 1); j < values.length; j++)
            {
                final long value2 = Long.parseLong(values[j]);
                final long diff = (value2 - value1);
                if (diff == k)
                {
                    count++;
                    break;
                }
                else if (diff > k)
                {
                    i = j - 1;
                    break;
                }

            }

        }
        System.out.println(count);

        // System.out.println(getIntegerComplement(13));

    }

    static int getIntegerComplement(final int n)
    {
        int ret = 0;
        final String bit = getBit(n);
        for (int i = 0; i < bit.length(); i++)
        {
            if (bit.charAt(i) == '0')
            {
                ret = (int) (ret + Math.pow(2, bit.length() - (i + 1)));
            }
        }

        return ret;

    }

    static String getBit(int n)
    {
        final StringBuffer buf = new StringBuffer();
        while (n > 0)
        {
            if (n == 2)
            {
                buf.insert(0, "10");
                break;
            }
            else
            {
                buf.insert(0, "" + (n % 2));
                n = n / 2;
            }
        }
        return buf.toString();
    }

}
