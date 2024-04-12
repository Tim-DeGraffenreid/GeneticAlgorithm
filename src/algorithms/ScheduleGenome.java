package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ScheduleGenome {
	private ArrayList<ActivityGene> schedule = new ArrayList<>();
	double fitness = 0.0;
	double confidence = 0.0;
	static Random rand = new Random();
	
	public static ScheduleGenome crossover(ArrayList<ActivityGene> first, ArrayList<ActivityGene> second){
		ArrayList<ActivityGene> crossed = new ArrayList<>();
		
		int splitAt = 5 + rand.nextInt(9 - 5);
		
		for(int i = 0; i < first.size(); i++) {
			first.get(i).resetFitness();
			second.get(i).resetFitness();
		}
		
		crossed.addAll(first.subList(0, splitAt));
		crossed.addAll(second.subList(splitAt, second.size()));
		
		return new ScheduleGenome(crossed);
	}
	
	public void resetFitness() {
		
		for(ActivityGene ag:schedule) {
			ag.resetFitness();
		}
		
		this.fitness = 0;
	}
	
	public void resetConfidence() {
		this.confidence = 0;
	}
	
	public ScheduleGenome() {
		
	}
	public ScheduleGenome(ArrayList<ActivityGene> schedule) {
		this.schedule = schedule;
	}
	/**
	 * @return the activity
	 */
	public ArrayList<ActivityGene> getSchedule() {
		return schedule;
	}
	/**
	 * @param activity the activity to set
	 */
	public void addActivity(ActivityGene activity) {
		this.schedule.add(activity);
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
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(ArrayList<ActivityGene> schedule) {
		this.schedule = schedule;
	}
	/**
	 * @return the confidence
	 */
	public double getConfidence() {
		return confidence;
	}
	/**
	 * @param confidence the confidence to set
	 */
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
	private void runFitnessTimeRoom() {
		//Activity is scheduled at the same time in the same room as another of the activities: -0.5
		if(schedule.size() > 1) {
			for(int i = 0; i < schedule.size() - 1; i++) {
				for(int j = i + 1; j < schedule.size(); j++) {
					if(schedule.get(i).getTime() == schedule.get(j).getTime() && 
							schedule.get(i).getRoom() == schedule.get(j).getRoom() ) {
						schedule.get(i).addToFitness(-0.5);
						break;
					}
				}
			}
		}
	}
	
	private void runFitnessRoomCapacity() {
		Activities actData = new Activities();
		Rooms roomData = new Rooms();
		
		for(ActivityGene ag:schedule) {
			int capacity = roomData.getRoom(ag.getRoom()).getCapacity();
			int enrollment = actData.getActivity(ag.getActivity()).getExpectedEnrollment();
			
			if(capacity < enrollment) {
				ag.addToFitness(-0.5);
			}else if((capacity/enrollment) > 6) {
				ag.addToFitness(-0.4);
			}else if((capacity/enrollment) > 3) {
				ag.addToFitness(-0.2);
			}else {
				ag.addToFitness(0.3);
			}	
		}
	}
	
	private void runFitnessFacilitator() {
	
		Activities actData = new Activities();
		for(ActivityGene ag:schedule) {
			String facilitator = ag.getFacilitator();
			Activity activity = actData.getActivity(ag.getActivity());
			
			if(activity.isPreferredFacilitator(facilitator)) {
				ag.addToFitness(0.5);
			}else if(activity.isOtherFacilitator(facilitator)) {
				ag.addToFitness(0.2);
			}else {
				ag.addToFitness(-0.1);
			}
		}	
	}
	
	public void runFitnessFacilitatorLoad() {
		Map<String, Integer> facilitatorTotalCount = new HashMap<>();

		for(int i = 0; i < schedule.size(); i++) {
		    String facilitator = schedule.get(i).getFacilitator();
		    facilitatorTotalCount.put(facilitator, facilitatorTotalCount.getOrDefault(facilitator, 0) + 1);
		}

		for(int i = 0; i < schedule.size(); i++) {
		    String facilitator = schedule.get(i).getFacilitator();
		    int time = schedule.get(i).getTime();
		    int totalActivities = facilitatorTotalCount.get(facilitator);

		    if (totalActivities > 4) {
		        schedule.get(i).addToFitness(-0.5);
		    }else if(totalActivities <= 2 && !facilitator.equals("Tyler")) {
		    	schedule.get(i).addToFitness(-0.4);
		    }

		    boolean isSoloActivityAtTime = true;
		    for(int j = 0; j < schedule.size(); j++) {
		        if (i != j && schedule.get(j).getFacilitator().equals(facilitator) && schedule.get(j).getTime() == time) {
		            isSoloActivityAtTime = false;
		            break;
		        }
		    }

		    if (isSoloActivityAtTime) {
		        schedule.get(i).addToFitness(0.2);
		    } else {
		        schedule.get(i).addToFitness(-0.2);
		    }
		}
	}
	
	public void runFitnessFacilitatorIsConsecutive() {

		for(int i = 0; i < schedule.size() - 1; i++) {

			for(int j = i + 1; j < schedule.size(); j++) {
				if(schedule.get(i).getTime() + 100 == schedule.get(j).getTime() 
						&& schedule.get(i).getFacilitator().equals(schedule.get(j).getFacilitator())) {

					if(( !(schedule.get(i).getRoom().contains("Roman") || schedule.get(i).getRoom().contains("Beach")) )
							&& (schedule.get(j).getRoom().contains("Roman") || schedule.get(j).getRoom().contains("Beach"))) {
						schedule.get(i).addToFitness(-0.4);	
					} else if(((schedule.get(i).getRoom().contains("Roman") || schedule.get(i).getRoom().contains("Beach")))
							&& !(schedule.get(j).getRoom().contains("Roman") || schedule.get(j).getRoom().contains("Beach"))) {
						schedule.get(i).addToFitness(-0.4);						
					}else {
						schedule.get(i).addToFitness(0.5);
					}
				}
			}
		}
	}
	
	public void runFitness101Sections() {
		int timeDiff = Math.abs(schedule.get(0).getTime() - schedule.get(1).getTime());
		
		if(timeDiff > 4) {
			schedule.get(0).addToFitness(0.5);
		}else if(timeDiff == 0) {
			schedule.get(0).addToFitness(-0.5);
		}	
	}
	
	public void runFitness191Sections() {
		int timeDiff = Math.abs(schedule.get(2).getTime() - schedule.get(3).getTime());
		
		if(timeDiff > 4) {
			schedule.get(2).addToFitness(0.5);
		}else if(timeDiff == 0) {
			schedule.get(2).addToFitness(-0.5);
		}	
	}
	
	public void runFitnessActivityTimeDifference() {
		
		int diff101A_191A = Math.abs(schedule.get(0).getTime() - schedule.get(2).getTime());
		int diff101A_191B = Math.abs(schedule.get(0).getTime() - schedule.get(3).getTime());
		int diff101B_191A = Math.abs(schedule.get(1).getTime() - schedule.get(2).getTime());
		int diff101B_191B = Math.abs(schedule.get(1).getTime() - schedule.get(3).getTime());
		
		if(diff101A_191A == 100) {
			boolean r1 = schedule.get(0).getRoom().contains("Roman") || schedule.get(0).getRoom().contains("Beach");
			boolean r2 = schedule.get(2).getRoom().contains("Roman") || schedule.get(2).getRoom().contains("Beach");
			if(r1 ^ r2) {
				schedule.get(0).addToFitness(-0.4);
			}else {
				schedule.get(0).addToFitness(0.5);
			}
		}else if(diff101A_191A == 200) {
			schedule.get(0).addToFitness(0.25);
		}else if (diff101A_191A == 0) {
			schedule.get(0).addToFitness(-0.25);
		}
		
		if(diff101A_191B == 100) {
			boolean r1 = schedule.get(0).getRoom().contains("Roman") || schedule.get(0).getRoom().contains("Beach");
			boolean r2 = schedule.get(3).getRoom().contains("Roman") || schedule.get(3).getRoom().contains("Beach");
			if(r1 ^ r2) {
				schedule.get(0).addToFitness(-0.4);
			}else {
				schedule.get(0).addToFitness(0.5);
			}
		}else if(diff101A_191B == 200) {
			schedule.get(0).addToFitness(0.25);
		}else if (diff101A_191B == 0) {
			schedule.get(0).addToFitness(-0.25);
		}
		
		if(diff101B_191A == 100) {
			boolean r1 = schedule.get(1).getRoom().contains("Roman") || schedule.get(1).getRoom().contains("Beach");
			boolean r2 = schedule.get(2).getRoom().contains("Roman") || schedule.get(2).getRoom().contains("Beach");
			if(r1 ^ r2) {
				schedule.get(1).addToFitness(-0.4);
			}else {
				schedule.get(1).addToFitness(0.5);
			}
		}else if(diff101B_191A == 200) {
			schedule.get(1).addToFitness(0.25);
		}else if (diff101B_191A == 0) {
			schedule.get(1).addToFitness(-0.25);
		}
		
		if(diff101B_191B == 100) {
			boolean r1 = schedule.get(1).getRoom().contains("Roman") || schedule.get(1).getRoom().contains("Beach");
			boolean r2 = schedule.get(3).getRoom().contains("Roman") || schedule.get(3).getRoom().contains("Beach");
			if(r1 ^ r2) {
				schedule.get(1).addToFitness(-0.4);
			}else {
				schedule.get(1).addToFitness(0.5);
			}
		}else if(diff101B_191B == 200) {
			schedule.get(1).addToFitness(0.25);
		}else if (diff101B_191B == 0) {
			schedule.get(1).addToFitness(-0.25);
		}	
	}
	
	public void runFitness() {
		double genomeFitness = 0.0;
		
		this.runFitnessTimeRoom();
		this.runFitnessRoomCapacity();
		this.runFitnessFacilitator();
		this.runFitnessFacilitatorIsConsecutive();
		this.runFitness101Sections();
		this.runFitness191Sections();
		this.runFitnessActivityTimeDifference();
		
		for(ActivityGene sg:schedule) {
			genomeFitness += sg.getFitness();
		}
		
		this.fitness = genomeFitness;
		
	}
	
	@Override
	public String toString() {
		return "ScheduleGenome [schedule=" + schedule + "]";
	}
	
}
