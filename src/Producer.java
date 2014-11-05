public class Producer
        implements Runnable
{
    private final Container container;

    public Producer(final Container container)
    {
        this.container = container;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 30; i++)
        {
            while (container.size() >= 10)
            {
                try
                {
                    System.out.println("Waiting for Consumer to Consume More string");
                    Thread.sleep(5000);
                }
                catch (final InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            container.add("Test");
            try
            {
                Thread.sleep(3000);
            }
            catch (final InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(final String[] args)
            throws InterruptedException
    {
        final Container c = new Container();
        final Thread producer = new Thread(new Producer(c));
        final Thread consumer = new Thread(new Consumer(c));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Finished");
    }

}
