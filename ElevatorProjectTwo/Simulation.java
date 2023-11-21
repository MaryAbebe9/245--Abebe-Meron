import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    private List<Elevator> elevators;
    private List<Floor> floors;
    private int duration;
    private double passengerProbability;
    private Random random;

    // Statistics
    private long totalTravelTime;
    private long longestTravelTime;
    private long shortestTravelTime;
    private int totalPassengers;

    public Simulation(PropertyManager propertyManager) {
        this.duration = Integer.parseInt(propertyManager.getProperty("duration"));
        this.passengerProbability = Double.parseDouble(propertyManager.getProperty("passengers"));
        random = new Random();

        initializeSimulation(propertyManager);
    }

    private void initializeSimulation(PropertyManager propertyManager) {
        // Initialize elevators
        int numElevators = Integer.parseInt(propertyManager.getProperty("elevators"));
        int elevatorCapacity = Integer.parseInt(propertyManager.getProperty("elevatorCapacity"));
        elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(elevatorCapacity));
        }

        // Initialize floors
        int numFloors = Integer.parseInt(propertyManager.getProperty("floors"));
        floors = new ArrayList<>();
        for (int i = 1; i <= numFloors; i++) {
            floors.add(new Floor(i));
        }

        // Reset statistics
        totalTravelTime = 0;
        longestTravelTime = 0;
        shortestTravelTime = Long.MAX_VALUE;
        totalPassengers = 0;
    }

    public void simulate() {
        for (int tick = 0; tick < duration; tick++) {
            // Generate new passengers
            generateNewPassengers(tick);

            // Elevator operations: unload and load passengers
            for (Elevator elevator : elevators) {
                // Example elevator operations
                PropertyManager  pm = new PropertyManager();
                Floor currentFloor;
                if (elevator.getCurrentFloor() - 1>Integer.parseInt(pm.getProperty("floors"))){
                    currentFloor = floors.get(Integer.parseInt(pm.getProperty("floors"))-1);
                }
                 else {currentFloor = floors.get(elevator.getCurrentFloor() - 1);}
                List<Passenger> disembarked = elevator.unloadPassengers();
                // Load passengers waiting for the elevator
                // This is simplified; you'll need to handle direction logic
                Passenger passenger;
                while ((passenger = currentFloor.removeNextPassenger()) != null && !elevator.isFull()) {
                    elevator.loadPassenger(passenger);
                }
                // Move elevator
                if (elevator.isGoingUp()) {
                    elevator.moveUp();
                } else {
                    elevator.moveDown();
                }
            }

            // Update statistics at the end of the tick
            updateStatistics();
        }
       
        // Report final statistics
        reportStatistics();
    }
     public void simulate(int i){
        Elevator.sim();
     }

    private void generateNewPassengers(int tick) {
        // Logic to generate new passengers based on probability
        for (Floor floor : floors) {
            if (random.nextDouble() < passengerProbability) {
                int destinationFloor;
                do {
                    destinationFloor = random.nextInt(floors.size()) + 1;
                } while (destinationFloor == floor.getFloorNumber());

                Passenger newPassenger = new Passenger(floor.getFloorNumber(), destinationFloor);
                newPassenger.setCreateTime(tick);
                floor.addPassenger(newPassenger);
                totalPassengers++;
            }
        }
    }

    private void updateStatistics() {
        // Implement logic to update totalTravelTime, longestTravelTime, shortestTravelTime
        // This requires tracking when each passenger reaches their destination
    }

    private void reportStatistics() {
        if (totalPassengers > 0) {
            long averageTravelTime = totalTravelTime / totalPassengers;
            System.out.println("Average travel time: " + averageTravelTime);
            System.out.println("Longest travel time: " + longestTravelTime);
            System.out.println("Shortest travel time: " + shortestTravelTime);
        } else {
            System.out.println("No passengers were transported.");
        }
    }
    

    // Additional methods as required for the simulation
}
