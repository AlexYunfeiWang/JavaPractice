public class WaitNotifyTest {
    public static void main(String[] args) {

        Message msg = new Message("process it");
        Waiter waiter1 = new Waiter(msg);
        Thread wait1 = new Thread(waiter1,"waiter");
        wait1.start();

        Waiter waiter2 = new Waiter(msg);
        Thread wait2 = new Thread(waiter2, "waiter1");
        wait2.start();

        Notifier notifier = new Notifier(msg);
        Thread notify = new Thread(notifier, "notifier");
        notify.start();

        try {
            wait1.join();
            wait2.join();
            notify.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        //wait for all other threads to complete before print the last message
        System.out.println("All the threads are started");
    }
}
