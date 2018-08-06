public class SynchronizedExample {
    static final Object lock = new Object();

    public static void main(String... args) {
        System.out.println("starting: " + Thread.currentThread());
        new Thread(new Runnable() {
            @Override
            public void run() {
               //The block of code below will execute as if it's a synchronized method.
                synchronized (lock) {
                   int counter = 0;
                   while(counter != 5){
                     try{
                        Thread.sleep(1000);
                        System.out.println("Counter at the moment: " + counter);
                        counter++;
                     }catch(Exception e){
                        e.printStackTrace();
                     }
                  }
                }
            }
        }).start();
        //The block of code below will execute as if it's a synchronized method.
        synchronized (lock) {
            System.out.println("finishing: " + Thread.currentThread());
        }
    }
}
