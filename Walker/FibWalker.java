package Walker;

public class FibWalker implements Runnable
{
  private static final boolean DEBUG = false;
  private final String NAME; // Name of the thread, set in constructor
  private long step = 0; // Fibonacci sequence number
  private long x = 1; // fib (step-2)
  private long y = 1; // Last sequence value: fib (step-1)
  private long z; // Current sequence value: fib (step)

  private Thread thisThread;

  public FibWalker(String name)
  {
    this.NAME = name;
  }

  public long fib(long n)
  {
    long previous = 0;
    long current = 1;
    long total = 0;
    for (int i = 0; i < n - 1; i++)
    {
      total = previous + current;
      previous = current;
      current = total;
    }
    return total;
  }

  @Override
  public void run()
  {
    if (DEBUG) System.out.println("Running " + this.NAME);
    while (true)
    {
      synchronized (this)
      {
        step++;
        // x = fib(step - 2);
        // y = fib(step - 1);
        // x = y;
        // y = z;
        if (z == 7540113804746346429L)
        {
          x = 1;
          y = 1;
        }
        z = x + y;
        x = y;
        y = z;
      }

      if (thisThread.isInterrupted())
      {
        System.out
            .println(this.NAME + " dies gracefully: " + this.step + ") " + this.x + ", " + this.y + ", " + this.z);
        return;
      }
    }
  }

  private void start()
  {
    if (DEBUG) System.out.println("Starting Thread " + this.NAME);
    if (thisThread == null)
    {
      thisThread = new Thread(this, this.getName());
      thisThread.start();
    }
    else if (DEBUG) System.out.println("Thread " + this.NAME + " is already running");
  }

  @Override
  public synchronized String toString()
  {
    return this.getName() + " " + this.step + ") " + this.x + ", " + this.y + ", " + this.z;
  }

  public String getName()
  {
    return this.NAME;
  }

  public static void main(String args[])
  {
    FibWalker walkerA = new FibWalker("Walker A");
    FibWalker walkerB = new FibWalker("Walker B");

    walkerA.start();
    walkerB.start();

    for (int i = 0; i < 5; i++)
    {
      try
      {
        Thread.sleep(2000);

        System.out.println(walkerA.toString());
        System.out.println(walkerB.toString());
        System.out.println();
      }
      catch (InterruptedException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    walkerA.thisThread.interrupt();
    walkerB.thisThread.interrupt();
  }
}
