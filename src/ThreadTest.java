public class ThreadTest
        implements Runnable
{

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(final String[] args)
            throws InterruptedException
    {
        final Thread t = new Thread(new ThreadTest());
        t.start();
        t.join();
        System.out.println("Sathish");

    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println(i);
        }

    }

}
