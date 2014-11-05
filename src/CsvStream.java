public class CsvStream
{
    char delimiter = ',';

    String encoding = "UTF-8";

    public void setEncoding(final String encoding)
    {
        this.encoding = encoding;
    }

    public void setDelimiter(final char delimiter)
    {
        this.delimiter = delimiter;
    }
}