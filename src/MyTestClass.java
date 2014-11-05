import java.lang.reflect.Modifier;

public class MyTestClass
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        try
        {
            final Class c = Class.forName("MyClass");
            // for (final Constructor constructor : c.getConstructors())
            // {
            // System.out.println(constructor.getParameterTypes().length);
            // }
            if ((!Modifier.isAbstract(c.getModifiers())) && hasDefaultConstructor(c))
            {
                final MyClass mc = (MyClass) c.newInstance();
            }
            System.out.println("finished");
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    private static boolean hasDefaultConstructor(final Class c)
    {
        try
        {
            c.getConstructor();
            return true;
        }
        catch (final SecurityException e)
        {
            return false;
        }
        catch (final NoSuchMethodException e)
        {
            return false;
        }
    }
}
