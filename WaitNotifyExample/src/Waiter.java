public class Waiter implements Runnable{
    /*
    A class that will wait for other threads to invoke
    notify methods to complete it’s processing.
    Notice that Waiter thread is owning monitor on
    Message object using synchronized block.
     */

    private Message msg;

    public Waiter(Message m) {
        this.msg = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        /*
        Note that the thread must be inside a synchronized block of code
        that synchronizes on the same object as the one on which wait()
        is being called, or in other words, the thread must hold the monitor
        of the object on which it'll call wait.

        If not so, an illegalMonitor exception is raised!
         */
        synchronized(msg) {
            try {
                System.out.println(name+" waiting to get notified at time:"
                        +System.currentTimeMillis());
                msg.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+" waiter thread got notified at time:"
                    +System.currentTimeMillis());

            //process the message now
            System.out.println(name+" processed: "+msg.getMsg());
        }
    }
}
