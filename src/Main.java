import static java.lang.Math.*;

public class Main {



    public static void main(String[] args) {

        int minCars = 2;
        int maxCars = 10;
        int capacity = maxCars - (minCars + maxCars)/2;

	    int numOfCars = args.length > 1 ? Integer.parseInt(args[1]) : (int) round(random() * (maxCars - minCars) + minCars);
	    Parking parking = new Parking(capacity);
	    System.out.println("with");
	    System.out.println("capacity: " + capacity);
	    System.out.println("numOfCars: " + numOfCars);
        System.out.println(capacity >= numOfCars? "no queue expected" : " some queue expected");
	    for (int i = 0; i<minCars; ++i) {
	        System.out.println("Car " + (i+1) + " starting");
            new Car(parking).start();
        }
	    for (int i = minCars; i < numOfCars; ++i) {
            System.out.println("Car " + (i+1) + " starting");
            new Car(parking).start();
        }
    }
}
