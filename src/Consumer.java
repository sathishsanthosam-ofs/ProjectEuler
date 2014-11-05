public class Consumer
        implements Runnable
{

    private final Container container;

    public Consumer(final Container container)
    {
        this.container = container;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(15000);
            for (int i = 0; i < 30; i++)
            {
                while (container.size() < 0)
                {

                    System.out.println("Waiting for Producer to Add More string");
                    Thread.sleep(5000);

                }
                container.remove();
                Thread.sleep(3000);

            }
        }
        catch (final InterruptedException e1)
        {
            System.out.println(e1.getMessage());
        }

    }
}
