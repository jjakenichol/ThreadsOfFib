package Walker;

public class FibWalker implements Runnable
{

  private final String NAME;      // Name of the thread, set in constructor
  private long         step  = 0; // Fibonacci sequence number
  private long         x     = 1; // fib (step-2)
  private long         y     = 1; // Last sequence value: fib (step-1)
  private long         z;         // Current sequence value: fib (step)
  private static long  start = 0;
  private static long  end   = 0;

  private Thread       thisThread;

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
    // TODO Auto-generated method stub

    System.out.println("Running " + this.NAME);
    while (end - start < 4000)
    {
      end = System.currentTimeMillis();

      step++;
      x = fib(step - 2);
      y = fib(step - 1);
      if (z == 7540113804746346429L)
      {
        x = 1;
        y = 1;
      }
      z = x + y;
      if ((end - start) % 2000 == 0) System.out.println(this.toString());
    }
    System.exit(0);
  }

  private void start()
  {
    System.out.println("Starting Thread" + this.NAME);
    if (thisThread == null)
    {
      thisThread = new Thread(this, this.getName());
      thisThread.start();
    }
    else System.out.println("Thread \"" + this.toString() + "\" is already running");

  }

  @Override
  public String toString()
  {
    return this.getName() + " " + this.step + ") " + this.x + " " + this.y + " " + this.z;
  }

  public String getName()
  {
    return this.NAME;
  }

  public static void main(String args[])
  {
    start = System.currentTimeMillis();

    FibWalker fibWalker1 = new FibWalker("fibWalker1");
    FibWalker fibWalker2 = new FibWalker("fibWalker2");

    fibWalker1.start();
    fibWalker2.start();

    // System.out.println(fibWalker1.fib(1000));
  }
}
