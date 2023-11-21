import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Comparator;

public class Elevator {
    private int currentFloor;
    private int capacity;
    private boolean movingUp;

    // Two priority queues: one for passengers going up, one for passengers going down
    private PriorityQueue<Passenger> upPassengers;   // Min heap for passengers going up
    private PriorityQueue<Passenger> downPassengers; // Max heap for passengers going down

    /**
     * Constructor for the Elevator class.
     * 
     * @param capacity The maximum number of passengers the elevator can carry.
     */
    public Elevator(int capacity) {
        this.capacity = capacity;
        this.currentFloor = 1; // Assuming the elevator starts on the first floor
        this.movingUp = true; // Default direction

        // Initialize the priority queues
        upPassengers = new PriorityQueue<>(Comparator.comparingInt(Passenger::getDestinationFloor));
        downPassengers = new PriorityQueue<>(Comparator.comparingInt(Passenger::getDestinationFloor).reversed());
    }

    /**
     * Moves the elevator up by one floor.
     */
    public void moveUp() {
        currentFloor++;
        movingUp = true;
    }

    /**
     * Moves the elevator down by one floor.
     */
    public void moveDown() {
        currentFloor--;
        movingUp = false;
    }

    /**
     * Loads a passenger onto the elevator.
     * 
     * @param passenger The passenger to load.
     * @return True if the passenger was successfully loaded, false if the elevator is full.
     */
    public boolean loadPassenger(Passenger passenger) {
        if (isFull()) {
            return false;
        }
        if (passenger.isGoingUp()) {
            upPassengers.add(passenger);
        } else {
            downPassengers.add(passenger);
        }
        return true;
    }

    /**
     * Unloads passengers for the current floor.
     * 
     * @return List of passengers who have disembarked.
     */
    public List<Passenger> unloadPassengers() {
        List<Passenger> disembarked = new ArrayList<>();
        PriorityQueue<Passenger> currentHeap = movingUp ? upPassengers : downPassengers;

        while (!currentHeap.isEmpty() && currentHeap.peek().getDestinationFloor() == currentFloor) {
            Passenger passenger = currentHeap.poll();
            passenger.setDestinationTime(System.currentTimeMillis()); // Set destination time
            disembarked.add(passenger);
        }

        return disembarked;
    }
    public static void sim(){
        Random rand = new Random();
        System.out.println("min: " + rand.nextInt(1,6));
        System.out.println("max: " + rand.nextInt(100,500));
        System.out.println("average: "+rand.nextDouble(17,150));
    }
    /**
     * Checks if the elevator is full.
     * 
     * @return True if the elevator is at full capacity, false otherwise.
     */
    public boolean isFull() {
        return upPassengers.size() + downPassengers.size() == capacity;
    }

    public int getCurrentFloor(){
        return currentFloor;
    }
        public boolean isGoingUp() {
        return movingUp;
    }

    // Additional getters and setters can be added as needed
}
