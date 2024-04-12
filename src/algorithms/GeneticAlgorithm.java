package algorithms;
import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;

public class GeneticAlgorithm {
	
	public ArrayList<double[]> log = new ArrayList<double[]>();
	Random rand = new Random();
	double mutationRate = 0.005;
	ArrayList<ScheduleGenome> population = new ArrayList<ScheduleGenome>();
	String[] facilitators = {"Lock", "Glen", "Banks", "Richards", "Shaw", "Singer", "Uther", "Tyler", "Numen", "Zeldin"};
	Activities activities = new Activities();
	Rooms rooms = new Rooms();
	String[] times = {"10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM"};
	
	public GeneticAlgorithm() {
		for(int i = 0; i < 500; i++) {
			ScheduleGenome s = new ScheduleGenome();
			for(Activity activity:activities.getActivities()) {
				Room[] rm = rooms.getRooms();
				s.addActivity(new ActivityGene(activity.getCourseId(),rm[rand.nextInt(rm.length)].name,
						times[rand.nextInt(times.length)],facilitators[rand.nextInt(facilitators.length)]));
			}
			population.add(s);
		}
		
		runFitness();
		population.sort(Comparator.comparingDouble(ScheduleGenome::getFitness).reversed());
		
		runLog();
	}
	
	/**
	 * Method to ensure unique random ints for selection & crossover
	 * @return
	 */
	public int[] getUniqueRandomInts() {
		
		int random1 = 0;
		int random2 = 0;
		
		while(random1 == random2) {
			random1 = rand.nextInt(population.size());
			random2 = rand.nextInt(population.size());
		}
		
		return new int[]{random1,random2};
	}
	
	public void runGeneration() {
		
		for(int i = 0; i < population.size(); i++) {
			int[] randos = getUniqueRandomInts();
			//Selection & Crossover
			ScheduleGenome offspring1 = ScheduleGenome.crossover(population.get(randos[0]).getSchedule(),
																population.get(randos[1]).getSchedule());
			ScheduleGenome offspring2 = ScheduleGenome.crossover(population.get(randos[1]).getSchedule(),
																population.get(randos[0]).getSchedule());
			runMutation(offspring1);
			offspring1.runFitness();			
			population.set(population.size() - 1, offspring1);
			population.set(population.size() - 2, offspring2);
			population.sort(Comparator.comparingDouble(ScheduleGenome::getFitness).reversed());
		}
		
		runLog();
	}
	
	public void runFitness() {
		for(ScheduleGenome s:population) {
			s.runFitness();
		}
		//runSoftMax();
	}
	
	public void runLog() {
		
		double max = 0.0;
		double avg = 0.0;
		double total = 0.0;
		double increase = 0.0;
		
		
		for(int i = 0; i < population.size(); i++) {
			total += population.get(i).getFitness();
			
			if(population.get(i).getFitness() > max) {
				max = population.get(i).getFitness();
			}
		}
		avg = total/population.size();
		
		if(this.log.size() == 0) {
			this.log.add(new double[]{max,avg,total, 0.0});
		}else {
			double previousAvg = this.log.get(this.log.size()-1)[1];
			increase = ((avg - previousAvg)/previousAvg) * 100 ;
			this.log.add(new double[]{max,avg,total,increase});
		}
	}
	
	public void runSoftMax() {
		
		  double expTotal = 0.0;
		  double[] expValues = new double[population.size()];
		  
		  double maxFitness = Double.NEGATIVE_INFINITY;
		  for (int i = 0; i < population.size(); i++) {
		    maxFitness = Math.max(maxFitness, population.get(i).getFitness());
		  }

		  // Shift the fitness values by subtracting the maximum
		  for (int i = 0; i < population.size(); i++) {
		    double shiftedFitness = population.get(i).getFitness() - maxFitness;
		    expValues[i] = Math.exp(shiftedFitness);
		    expTotal += expValues[i];
		  }

		  // Normalize by dividing each element by the sum of exponentials
		  for (int i= 0; i < expValues.length; i++) {
		    population.get(i).setConfidence(expValues[i] / expTotal);
		  }
		  
		  population.sort(Comparator.comparingDouble(ScheduleGenome::getConfidence).reversed());  	
		  for(ScheduleGenome sg:population) {
			  System.out.println(sg.getFitness());
		  }
	}
	
	public void runMutation(ScheduleGenome genome) {
		//Mutate facilitator if rand less than mutation_rate
		for (int i = 0; i < genome.getSchedule().size(); i++) {
			if (rand.nextDouble() < mutationRate) {
				genome.getSchedule().get(i).setFacilitator(facilitators[rand.nextInt(facilitators.length)]);
			}
		}	
	}
	
	public void resetFitness() {
		for(ScheduleGenome sg:population) {
			sg.resetConfidence();
			sg.resetFitness();
		}
	}
	
	/**
	 * @return the population
	 */
	public ArrayList<ScheduleGenome> getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(ArrayList<ScheduleGenome> population) {
		this.population = population;
	}

	/**
	 * @return the facilitators
	 */
	public String[] getFacilitators() {
		return facilitators;
	}

	/**
	 * @param facilitators the facilitators to set
	 */
	public void setFacilitators(String[] facilitators) {
		this.facilitators = facilitators;
	}

	/**
	 * @return the activities
	 */
	public Activities getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(Activities activities) {
		this.activities = activities;
	}

	/**
	 * @return the rooms
	 */
	public Rooms getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}

	/**
	 * @return the times
	 */
	public String[] getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(String[] times) {
		this.times = times;
	}
	
}
