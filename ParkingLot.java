/**
 * @author Mehrdad Sabetzadeh, University of Ottawa
 */
public class ParkingLot {

	// IMPORTANT: You are *discouraged* from defining any new instance variables in
	// ParkingLot. You are expected to provide a list-based implementation of
	// ParkingLot. Defining new instance variables can take you away from this
	// implementation goal and thus result in the loss of marks.
	/**
	 * List for storing occupancy information in the lot
	 */
	private List<Spot> occupancy;

	/**
	 * The maximum number of cars that the lot can accommodate
	 */
	private int capacity;

	/**
	 * Constructs a parking lot with a given (maximum) capacity
	 * 
	 * @param capacity is the (maximum) capacity of the lot
	 */
	public ParkingLot(int capacity) {

		if (capacity < 0) {
			
			throw new IllegalArgumentException("Max capacity cannot be less than 0");
			// Hint: throw a suitable exception here.
		
		}

		this.capacity = capacity;
		this.occupancy = new SinglyLinkedList<Spot>();
	}

	/**
	 * Parks a car (c) in the parking lot.
	 * 
	 * @param c         is the car to be parked
	 * @param timestamp is the (simulated) time when the car gets parked in the lot
	 */
	public void park(Car c, int timestamp) {
		
		if(c==null) {
			throw new NullPointerException("Cannot park a null car");
		}
		
		if(timestamp<0) {
			throw new IllegalArgumentException("Time should not be negative");
		}
		
		Spot newSpot= new Spot(c, timestamp);
		this.occupancy.add(newSpot);
		
		//throw new UnsupportedOperationException("This method has not been implemented yet!");
	
	}

	/**
	 * Removes the car (spot) parked at list index i in the parking lot
	 * 
	 * @param i is the index of the car to be removed
	 * @return the car (spot) that has been removed
	 */
	public Spot remove(int i) {
		if (this.occupancy.size()<=i) {
			throw new ArrayIndexOutOfBoundsException("This is not a valid spot to remove");
		}
		
		Spot removedCar;
		removedCar = this.occupancy.remove(i);
		return removedCar;
		//throw new UnsupportedOperationException("This method has not been implemented yet!");
	
	}

	
	public boolean attemptParking(Car c, int timestamp) {
		
		
		if(this.occupancy.size()<this.capacity) {
			try {
				park(c, timestamp);
				return true;
			}
			
			catch (NullPointerException e){
				System.out.println("Cannot park a null car");
				return false;
			}
			
			catch(IllegalArgumentException e) {
				System.out.println("Time should not be negative");
				return false;
			}
		}
		
		return false;
	
	}

	/**
	 * @return the capacity of the parking lot
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Returns the spot instance at a given position (i, j)
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return the spot instance at a given position (i, j)
	 */
	public Spot getSpotAt(int i) {
		
		if (i>=this.occupancy.size()) {
			throw new ArrayIndexOutOfBoundsException("This is not a valid spot to get");
		}
		
		return occupancy.get(i);

	}

	/**
	 * @return the total number of cars parked in the lot
	 */
	public int getOccupancy() {

		if(this.occupancy==null){
			throw new NullPointerException("Cannot get occupancy when list is null");
		}

		return this.occupancy.size();
	}

	/**
	 * @return String representation of the parking lot
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Total capacity: " + this.capacity + System.lineSeparator());
		buffer.append("Total occupancy: " + this.occupancy.size() + System.lineSeparator());
		buffer.append("Cars parked in the lot: " + this.occupancy + System.lineSeparator());

		return buffer.toString();
	}
}