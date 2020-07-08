import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.Objects.requireNonNull;
public class Parking {


    private HashSet <Car> slots;
    private int capacity;   // non-optional
    private int parked;

    private Queue<Car> queue;

    public Parking (int capacity){
        if (capacity < 0)
            throw new IllegalArgumentException("capacity is negative");
        this.capacity = capacity;
        slots = new HashSet<Car>(capacity);
        parked = 0;
        queue = new ConcurrentLinkedQueue<Car>();
    }
    public void enter(Car car) throws InterruptedException {
        requireNonNull(car);
        synchronized (car) {
            if (parked < capacity) {
                if (!slots.add(car))
                    throw new IllegalArgumentException("car is already parked here");
                parked++;
                System.out.println(" parked car " + car.getId() + " as " + parked );
            } else {
                System.out.println(" parking is full, car " + car.getId() + " added to queue");
                queue.add(car);
                car.wait();
            }
        }
    }


    public void exit(Car car) {
        requireNonNull(car);
        synchronized (car) {
            if (!slots.remove(car))
                throw new IllegalArgumentException("car isn't parked here");
        }
            if (!queue.isEmpty()) {
                Car removedFromQueue;
                synchronized (slots) {
                    slots.add(removedFromQueue = queue.poll());
                }
                synchronized (removedFromQueue) {
                    System.out.println(car.getId() + " parking is emptying, car " + removedFromQueue.getId() + " finally parked!");
                    removedFromQueue.notify();
                }
            }
            else
                parked--;
    }
}
