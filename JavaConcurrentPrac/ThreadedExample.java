import java.lang.Thread;

//Referring Android Concurrency.
public class ThreadedExample {
    public static void main(String... args) {
        System.out.println("starting: " + Thread.currentThread());
        new Thread() {
            @Override
            public void run() { //I am guessing this works exactly like a fork.
                int counter = 0;
               System.out.println("running: " + Thread.currentThread());
               while(counter != 10){
                  try{
                     Thread.currentThread().sleep(1000);
                     System.out.println("Counter at the moment: " + counter);
                     counter++;
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            }
        }.start();
        System.out.println("finishing: " + Thread.currentThread());
    }
}
