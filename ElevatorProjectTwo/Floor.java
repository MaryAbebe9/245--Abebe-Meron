import java.util.LinkedList;
import java.util.Queue;

public class Floor {
    private int floorNumber;
    private Queue<Passenger> waitingPassengers;

    /**
     * Constructor for the Floor class.
     * 
     * @param floorNumber The number of this floor.
     */
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.waitingPassengers = new LinkedList<>();
    }

    /**
     * Adds a passenger to the waiting queue.
     * 
     * @param passenger The passenger to add.
     */
    public void addPassenger(Passenger passenger) {
        waitingPassengers.add(passenger);
    }

    /**
     * Removes and returns the next passenger in the queue.
     * 
     * @return The next passenger, or null if the queue is empty.
     */
    public Passenger removeNextPassenger() {
        return waitingPassengers.poll();
    }

    /**
     * Gets the number of passengers waiting on this floor.
     * 
     * @return The number of waiting passengers.
     */
    public int getWaitingPassengerCount() {
        return waitingPassengers.size();
    }

    public int getFloorNumber(){
        return floorNumber;
    }

    // public static void main(String[] args) {
    //     // Create a floor (e.g., 5th floor)
    //     Floor floor = new Floor(5);

    //     // Create some passengers
    //     Passenger passenger1 = new Passenger(5, 8); // Passenger going from 5th to 8th floor
    //     Passenger passenger2 = new Passenger(5, 3); // Passenger going from 5th to 3rd floor

    //     // Add passengers to the floor
    //     floor.addPassenger(passenger1);
    //     floor.addPassenger(passenger2);

    //     // Display the number of passengers waiting
    //     System.out.println("Number of passengers waiting on floor " + floor.getWaitingPassengerCount());

    //     // Simulate a passenger boarding an elevator
    //     Passenger boardedPassenger = floor.removeNextPassenger();
    //     System.out.println("Passenger boarded to floor: " + boardedPassenger.getDestinationFloor());

    //     // Display the remaining number of passengers waiting
    //     System.out.println("Number of passengers waiting after boarding: " + floor.getWaitingPassengerCount());
    // }
}
