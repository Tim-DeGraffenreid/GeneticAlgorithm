package cli;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

import algorithms.ActivityGene;
import algorithms.GeneticAlgorithm;
import algorithms.ScheduleGenome;

public class CLI {
	
	public CLI() {
		start();
	}
	
    public void start() {
    	File file = new File("OptimalSchedule.txt");
    	GeneticAlgorithm ga = new GeneticAlgorithm();
    	int i = 0;
    	while(i < 100) {
    		ga.runGeneration();
    		i++;
    	}
    	
    	double g100 = ga.log.get(100)[3];
    	
    	do {
    		
    		ga.runGeneration();
    		i++;
 
    	}while((ga.log.get(i)[3] - 0.01) > g100);
   
    	
    	for(int j = 0; j < ga.log.size(); j ++) {
    		System.out.printf("Gen %d: ", j);
    		System.out.printf("Max: %.4f, ", ga.log.get(j)[0]);
    		System.out.printf("Avg: %.4f, ", ga.log.get(j)[1]);
    		System.out.printf("Total: %.4f, ", ga.log.get(j)[2]);
    		System.out.printf("Improvement: %.4f \n", ga.log.get(j)[3]);
    	}
    	
    	String optimalLog = "Optimal Schedule \n\n";
    	int index = ga.log.size() - 2;
    	optimalLog += String.format("Generation: %d, Fitness: %.4f, AvgFitness: %.4f, TotalFitness: %.4f"
    			,index + 1, ga.log.get(index)[0], ga.log.get(index)[1], ga.log.get(index)[2]);
    	
    	ScheduleGenome optimalSchedule = ga.getPopulation().get(0);
    	String schedule = "\n";
    	for(ActivityGene ag:optimalSchedule.getSchedule()) {
    		schedule += String.format("%s ",ag.getActivity());
    		schedule += String.format("\n\tFacilitator: %s", ag.getFacilitator());
    		schedule += String.format("\n\tRoom: %s", ag.getRoom());
    		schedule += String.format("\n\tTime: %d \n", ag.getTime());
    	}
    	
    	System.out.println( optimalSchedule);
    	PrintWriter outputFile;
    	
    	
		try {
			outputFile = new PrintWriter(file);
			outputFile.println(optimalLog);
			outputFile.println(schedule);
			outputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
}
