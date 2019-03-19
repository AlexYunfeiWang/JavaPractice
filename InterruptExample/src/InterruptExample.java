class InterruptExample {

    static public void example() throws InterruptedException {

        final Thread sleepyThread = new Thread(new Runnable() {

            public void run() {
                try {
                    System.out.println("I am too sleepy... Let me sleep for an hour.");
                    Thread.sleep(1000 * 60 * 60);
                } catch (InterruptedException ie) {
                    System.out.println("The interrupt flag is cleard : "
                            + Thread.interrupted() + " "
                            + Thread.currentThread().isInterrupted());
                    System.out.println("current thread: " + Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                    System.out.println("Oh someone woke me up ! ");
                    System.out.println("The interrupt flag is set now : "
                            + Thread.currentThread().isInterrupted()
                            + " " + Thread.interrupted());

                }
            }
        }, "sleepThread");

        sleepyThread.start();
        Thread.currentThread().sleep(2000);

        System.out.println("About to wake up the sleepy thread ...");
        sleepyThread.interrupt();

        Thread.currentThread().sleep(2000);
        System.out.println("Woke up sleepy thread ...");

        sleepyThread.join();
    }
}