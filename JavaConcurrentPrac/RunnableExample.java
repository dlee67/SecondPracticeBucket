//By using Runnable, any class can use run() now.
public class RunnableExample {
    public static void main(String... args) {
        System.out.println("starting: " + Thread.currentThread());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running: " + Thread.currentThread());
            }
        }).start();
        System.out.println("finishing: " + Thread.currentThread());
    }
}
