public class Passenger {
    public int currentFloor;
    private int destinationFloor;
     // Existing member variables...
     private long createTime; // Time when the passenger was created
     private long destinationTime; // Time when the passenger reached their destination

    /**
     * Constructor for the Passenger class.
     * 
     * @param currentFloor The floor where the passenger currently is.
     * @param destinationFloor The floor where the passenger wants to go.
     */
    public Passenger(int currentFloor, int destinationFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }

    /**
     * Gets the current floor of the passenger.
     * 
     * @return The current floor.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Gets the destination floor of the passenger.
     * 
     * @return The destination floor.
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * Determines if the passenger is moving upwards.
     * 
     * @return True if the passenger is moving up, false otherwise.
     */
    public boolean isGoingUp() {
        return destinationFloor > currentFloor;
    }

    /**
     * Determines if the passenger is moving downwards.
     * 
     * @return True if the passenger is moving down, false otherwise.
     */
    public boolean isGoingDown() {
        return destinationFloor < currentFloor;
    }
        // New methods to set and get the times
    public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    
    public void setDestinationTime(long destinationTime) {
            this.destinationTime = destinationTime;
        }
    
    public long getTravelTime() {
            return destinationTime - createTime;
        }
    public long getCreateTime(){
        return createTime;
    }
    public long getDestinationTime(){
        return destinationTime;
    }
}



