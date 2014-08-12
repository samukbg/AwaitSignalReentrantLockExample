import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by samukbg on 12/08/14.
 */
public class AwaitSignal {
    private int count = 0;
    private int numThread = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public void increment(){
        for(int i=0; i<10000; i++){
            count++;
        }
        System.out.println("Counter for thread"+numThread+" = "+count);
    }

    public void thread1() throws InterruptedException{

        lock.lock();
        System.out.println("Wainting for thread 2...");

        cond.await();
        System.out.println("Woken up!");

        try{
            numThread++;
            increment();
        }
        finally{
            lock.unlock();
        }
    }

    public void thread2() throws InterruptedException{

        lock.lock();

        System.out.println("Press Enter to wake the thread 1: ");
        new Scanner(System.in).nextLine();
        cond.signal();

        try{
            numThread++;
            increment();
        }
        finally{
            lock.unlock();
        }
    }

    public void finalCounter(){
        System.out.println("Final Counter = " + count);
    }

}


