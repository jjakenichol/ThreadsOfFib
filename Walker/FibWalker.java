package Walker;

/**
 * This class implements two threads that walk fibonacci.
 * 
 * @author J. Jake Nichol Date: 09-17-2014
 */
public class FibWalker implements Runnable
{
  /** The Constant DEBUG. */
  private static final boolean DEBUG = false;

  /** The thread name. */
  private final String NAME; // Name of the thread, set in constructor

  /** The fib step (sequence count). */
  private long step = 0;

  /** The previous-previous fib number, x. */
  private long x = 1;

  /** The previous fib number, y. */
  private long y = 1; // Last sequence value: fib (step-1)

  /** The current fib number, z. */
  private long z; // Current sequence value: fib (step)

  /** The thread object. */
  private Thread thisThread;

  /**
   * Instantiates a new fibWalker.
   *
   * @param name
   *          the name
   */
  public FibWalker(String name)
  {
    this.NAME = name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run()
  {
    if (DEBUG) System.out.println("Running " + this.NAME);
    while (true)
    {
      synchronized (this)
      {
        x = y;
        y = z;

        step++;
        if (z == 7540113804746346429L)
        {
          x = 1;
          y = 1;
        }
        z = x + y;

        if (thisThread.isInterrupted())
        {
          System.out.println(this.NAME + " dies gracefully: " + this.step + ") " + this.x + ", " + this.x + ", "
              + this.z);
          return;
        }
      }
    }
  }

  /**
   * Start.
   */
  private void start()
  {
    if (DEBUG) System.out.println("Starting Thread " + this.NAME);
    if (thisThread == null)
    {
      thisThread = new Thread(this, this.NAME);
      thisThread.start();
    }
    else if (DEBUG) System.out.println("Thread " + this.NAME + " is already running");
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public synchronized String toString()
  {
    return this.NAME + " " + this.step + ") " + this.x + ", " + this.y + ", " + this.z;
  }

  public static void main(String args[])
  {
    FibWalker walkerA = new FibWalker("Walker A");
    FibWalker walkerB = new FibWalker("Walker B");

    walkerA.start();
    walkerB.start();

    for (int i = 0; i < 10; i++)
    {
      try
      {
        Thread.sleep(2000);

        System.out.println(walkerA);
        System.out.println(walkerB);
        System.out.println();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }

    walkerA.thisThread.interrupt();
    walkerB.thisThread.interrupt();

    try
    {
      walkerA.thisThread.join();
      walkerB.thisThread.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
