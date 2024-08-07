public class CapacityOptimizer {
	private static final int NUM_RUNS = 10;

	private static final double THRESHOLD = 5.0d;

	public static int getOptimalNumberOfSpots(int hourlyRate) {
		
		int lotSize=1;
		boolean flag=true;
		
		while(flag){
			
			double total=0.0;
			double average;

			System.out.println("\n==== Setting lot capacity to: "+lotSize+"====");
			for (int i=0;i<NUM_RUNS;i++) {
				ParkingLot currentLot= new ParkingLot(lotSize);
				Simulator currentSim= new Simulator(currentLot, hourlyRate,86400);
				long startTime=System.currentTimeMillis();
				currentSim.simulate();
				long endTime =System.currentTimeMillis();
				long timeTook=endTime-startTime;
				double presentTotal=currentSim.getIncomingQueueSize();
				total+=presentTotal;
				System.out.println("Simulation run " +(i+1)+" ("+ timeTook+"ms) Queue length at the end of simulation run: "+presentTotal);
			}
			
			average=total/NUM_RUNS;
			
			if(average<=5) {
				break;
			}
			
			else {
				lotSize++;
			}
		}
		return lotSize;
	
	}

	public static void main(String args[]) {
		StudentInfo.display();

		long mainStart = System.currentTimeMillis();

		if (args.length < 1) {
			System.out.println("Usage: java CapacityOptimizer <hourly rate of arrival>");
			System.out.println("Example: java CapacityOptimizer 11");
			return;
		}

		if (!args[0].matches("\\d+")) {
			System.out.println("The hourly rate of arrival should be a positive integer!");
			return;
		}

		int hourlyRate = Integer.parseInt(args[0]);

		int lotSize = getOptimalNumberOfSpots(hourlyRate);

		System.out.println();
		System.out.println("SIMULATION IS COMPLETE!");
		System.out.println("The smallest number of parking spots required: " + lotSize);

		long mainEnd = System.currentTimeMillis();

		System.out.println("Total execution time: " + ((mainEnd - mainStart) / 1000f) + " seconds");

	}
}