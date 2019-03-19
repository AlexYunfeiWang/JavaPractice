public class Notifier implements Runnable{
    /*
    A class that will process on Message object and then
    invoke notify method to wake up threads waiting for
    Message object. Notice that synchronized block is
    used to own the monitor of Message object.
     */

    private Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+" started");
        try {
            Thread.sleep(1500);
            synchronized (msg) {
                msg.setMsg(name+" Notifier work done");
                //msg.notify();
                msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
