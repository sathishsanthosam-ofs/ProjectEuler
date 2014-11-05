import java.util.ArrayList;
import java.util.List;


public class Container
{
    List<String> container = new ArrayList<String>();

    public void add(final String word)
    {
        container.add(word);
        System.out.println("Producer has Added a String. Container Size is  : " + size());
    }

    public void remove()
    {
        container.remove(0);
        System.out.println("Consumer has Removed a String. Container Size is  : " + size());
    }

    public int size()
    {
        return container.size();
    }
}
