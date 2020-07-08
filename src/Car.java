
import static java.util.Objects.requireNonNull;

public class Car extends Thread {
    private static int nextId = 1;
    private int id;
    private Parking parking;    // non-optional

    public Car(Parking parking){
         this.parking = requireNonNull(parking);
         id = nextId++;
    }

    @Override
    public long getId() {
        return id;
    }

    //
    @Override
    public void run() {
        long sleepTime = Math.round(Math.random() * 5) * 1000; // from 0 to 5 seconds
        //System.out.println("Car number " + this.getId() + " is entering");
        try {
            parking.enter(this);
            sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parking.exit(this);
        System.out.println("Car number " + this.getId() + " is exiting after " + sleepTime + " milliseconds have elapsed");
    }
}
