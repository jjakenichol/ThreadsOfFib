package Walker;
public class FibWalker implements Runnable {

  private final String NAME;    // Name of the thread, set in constructor
  private long step = 0;        // Fibonacci sequence number
  private long x = 1;           // fib
  private long y = 1;           // Last sequence value: fib (Step-1)
  private long z;               // Current sequence value: fib (Step)
  private Thread thisThread;

  public FibWalker(String name) {
    this.NAME = name;
  }

  @Override
  public void run()
  {
    // TODO Auto-generated method stub

  }

  private void start()
  {
    System.out.println("Starting Thread" + this.toString());
    if (thisThread == null)
    {
      thisThread = new Thread(this, this.getName());
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

  public static void main(String args[]) {
    FibWalker fibWalker1 = new FibWalker("fibWalker1");
    FibWalker fibWalker2 = new FibWalker("fibWalker2");

    fibWalker1.start();
    fibWalker2.start();
  }

}
