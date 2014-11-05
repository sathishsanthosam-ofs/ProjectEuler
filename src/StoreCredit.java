import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreCredit
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args)
            throws IOException
    {
        final BufferedReader br = new BufferedReader(new FileReader(new File(
                "C:/Documents and Settings/sathishkumars/My Documents/Downloads/A-large-practice.in")));

        final BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                "C:/Documents and Settings/sathishkumars/My Documents/Downloads/output.txt")));
        String temp = "";
        final List<TestSet> testSet = new ArrayList<StoreCredit.TestSet>();
        TestSet current = null;
        int i = 0;
        int index = 1;
        while ((temp = br.readLine()) != null)
        {
            if (i == 1)
            {
                current = new TestSet();
                testSet.add(current);
                current.setCreditLimit(Integer.parseInt(temp));
                current.setIndex(index);
                index++;
            }
            else if (i == 3)
            {
                final String[] priceList = temp.split(" ");
                current.setPriceList(priceList);
                i = 0;
            }
            i++;
        }
        br.close();
        for (int j = 0; j < testSet.size(); j++)
        {
            bw.write(testSet.get(j).findOutput());
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
        private int creditLimit;

        private String[] priceList;

        private int index;

        public int getCreditLimit()
        {
            return creditLimit;
        }

        public void setCreditLimit(final int creditLimit)
        {
            this.creditLimit = creditLimit;
        }

        public String[] getPriceList()
        {
            return priceList;
        }

        public void setPriceList(final String[] priceList)
        {
            this.priceList = priceList;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(final int index)
        {
            this.index = index;
        }

        public String findOutput()
        {
            for (int i = 0; i < priceList.length; i++)
            {
                final int first = Integer.parseInt(priceList[i]);
                for (int j = 0; j < priceList.length; j++)
                {
                    final int second = Integer.parseInt(priceList[j]);
                    if ((first + second) == creditLimit && (i != j))
                    {
                        return "Case #" + index + ": " + (i + 1) + " " + (j + 1);
                    }
                }
            }

            return null;
        }

    }

}
