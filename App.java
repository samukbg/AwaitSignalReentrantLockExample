/**
 * Created by samukbg on 12/08/14.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Initiating AwaitSignal class...");
        final AwaitSignal as = new AwaitSignal();

        Runnable runnable1 = new Runnable() {
            public void run(){
                try{
                    as.thread1();
                }catch (InterruptedException e){}
            }
        };

        Runnable runnable2 = new Runnable() {
            public void run(){
                try{
                    as.thread2();
                }catch (InterruptedException e){}
            }
        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        as.finalCounter();
    }
}
