package algorithms;

public class ActivityGene {

	String activity;
	String room;
	int time;
	String facilitator;
	double fitness;

	/**
	 * @param activity
	 * @param room
	 * @param time
	 * @param facilitator
	 */
	public ActivityGene(String activity, String room, String time, String facilitator) {
		
		this.activity = activity;
		this.room = room;
		this.time = convertToMilitaryTime(time);
		this.facilitator = facilitator;
		this.fitness = 0;
	}
	
	public static int convertToMilitaryTime(String standardTime) {

		String[] parts = standardTime.split(" ");
		int hour = Integer.parseInt(parts[0]);
		String amPm = parts[1];

		if ("PM".equals(amPm) && hour != 12) {
			hour += 12;
		} else if ("AM".equals(amPm) && hour == 12) {
			hour = 0; 
		}

		return hour * 100;
	}
	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the facilitator
	 */
	public String getFacilitator() {
		return facilitator;
	}

	/**
	 * @param facilitator the facilitator to set
	 */
	public void setFacilitator(String facilitator) {
		this.facilitator = facilitator;
	}

	/**
	 * @return the fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * @param fitness the fitness to set
	 */
	public void addToFitness(double fitness) {
		this.fitness += fitness;
	}
	
	//Only used during crossover
	public void resetFitness() {
		
		this.fitness = 0;
	}

	@Override
	public String toString() {
		return "ActivityGene [activity=" + activity + ", room=" + room + ", time=" + time + ", facilitator="
				+ facilitator + "]";
	}
	
	
}
