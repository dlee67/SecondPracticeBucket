class OnMyOwnTwo{

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
            synchronized(lockTwo){
               try{
                  lockOne.wait();
                  for(int counterTwo = 0; counterTwo < 100000; counterTwo++){
                     increment();
                  }
               }catch (Exception e){
                  e.printStackTrace();
               }
            }
         }
      });

      Thread threadTwo = new Thread( new Runnable(){
         @Override
         public void run(){
            synchronized(lockOne){
               try{
                  lockTwo.wait();
                  for(int counterTwo = 0; counterTwo < 100000; counterTwo++){
                        increment();
                  }
               } catch (Exception e){
                  e.printStackTrace();
               }
            }
         }
      });

      threadOne.start();
      threadTwo.start();
      threadOne.join();
      threadTwo.join();
      System.out.println("somevalue at the moment: " + somevalue);
   }
}
