import java.io.IOException;


class Test
{

    private final int id;

    private final String name;

    public Test(final int id, final String name)
    {
        this.id = id;
        this.name = name;
    }

    public static void main(final String[] args)
            throws IOException
    {

    }

    public boolean equals(final Object obj)
    {
        if (obj instanceof Test)
        {
            return (this.id == ((Test) obj).id);
        }
        else
        {
            return false;
        }

    }

    @Override
    public int hashCode()
    {
        return id;
    }


}
