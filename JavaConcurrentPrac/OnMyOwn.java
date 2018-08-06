class OnMyOwn{

   final static Object lockOne = new Object();
   final static Object lockTwo = new Object();
   static int somevalue = 0;

   //With the keyword synchronized, only one Thread can execute it.
   public static synchronized void increment(){
      somevalue++;
   }

   public static void main(String args[]) throws Exception{

      Thread threadOne = new Thread( new Runnable(){
         @Override
         public void run(){
            for(int counterOne = 0; counterOne < 100000; counterOne++){
               increment();
            }
         }
      });

      Thread threadTwo = new Thread( new Runnable(){
         @Override
         public void run(){
            for(int counterTwo = 0; counterTwo < 100000; counterTwo++){
               increment();
            }
         }
      });

      threadOne.start();
      threadTwo.start();
      threadOne.join();
      threadTwo.join();
      System.out.println("Counter at the momement: " + somevalue);

   }
}
