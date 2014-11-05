import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReverseWords
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args)
            throws IOException
    {
        final BufferedReader br = new BufferedReader(new FileReader(new File(
                "C:/Documents and Settings/sathishkumars/My Documents/Downloads/B-large-practice.in")));

        final BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                "C:/Documents and Settings/sathishkumars/My Documents/Downloads/output.txt")));
        String temp = "";
        final List<TestSet> testSet = new ArrayList<ReverseWords.TestSet>();
        TestSet current = null;
        int i = 0;
        int index = 1;
        while ((temp = br.readLine()) != null)
        {
            if (i != 0)
            {
                current = new TestSet();
                testSet.add(current);
                current.setIndex(index);
                final String[] priceList = temp.split(" ");
                current.setInput(priceList);
                index++;
            }
            i++;
        }
        br.close();
        for (int j = 0; j < testSet.size(); j++)
        {
            bw.write(testSet.get(j).getOutput());
            if (j != (testSet.size() - 1))
            {
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();

    }

    static class TestSet
    {
        private String[] input;

        int index;

        public String[] getInput()
        {
            return input;
        }

        public void setInput(final String[] input)
        {
            this.input = input;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(final int index)
        {
            this.index = index;
        }

        public String getOutput()
        {
            final StringBuffer ret = new StringBuffer();
            for (int i = input.length - 1; i >= 0; i--)
            {
                ret.append(input[i]);
                if (i != 0)
                {
                    ret.append(" ");
                }

            }
            return "Case #" + index + ": " + ret.toString();
        }
    }

}
