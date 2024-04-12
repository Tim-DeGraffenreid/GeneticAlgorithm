package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Activities {
    private List<Activity> activities;

    public Activities() {
        this.activities = new ArrayList<>();

        
        this.activities.add(new Activity(
            "SLA100A",
            50,
            Arrays.asList("Glen", "Lock", "Banks", "Zeldin"),
            Arrays.asList("Numen", "Richards")));

        this.activities.add(new Activity(
            "SLA100B",
            50,
            Arrays.asList("Glen", "Lock", "Banks", "Zeldin"),
            Arrays.asList("Numen", "Richards")));

        this.activities.add(new Activity(
            "SLA191A",
            50,
            Arrays.asList("Glen", "Lock", "Banks", "Zeldin"),
            Arrays.asList("Numen", "Richards")));

        this.activities.add(new Activity(
            "SLA191B",
            50,
            Arrays.asList("Glen", "Lock", "Banks", "Zeldin"),
            Arrays.asList("Numen", "Richards")));

        this.activities.add(new Activity(
            "SLA201",
            50,
            Arrays.asList("Glen", "Banks", "Zeldin", "Shaw"),
            Arrays.asList("Numen", "Richards", "Singer")));

        this.activities.add(new Activity(
            "SLA291",
            50,
            Arrays.asList("Lock", "Banks", "Zeldin", "Singer"),
            Arrays.asList("Numen", "Richards", "Shaw", "Tyler")));

        this.activities.add(new Activity(
            "SLA303",
            60,
            Arrays.asList("Glen", "Zeldin", "Banks"),
            Arrays.asList("Numen", "Singer", "Shaw")));

        this.activities.add(new Activity(
            "SLA304",
            25,
            Arrays.asList("Glen", "Banks", "Tyler"),
            Arrays.asList("Numen", "Singer", "Shaw", "Richards", "Uther", "Zeldin")));

        this.activities.add(new Activity(
            "SLA394",
            20,
            Arrays.asList("Tyler", "Singer"),
            Arrays.asList("Richards", "Zeldin")));

        this.activities.add(new Activity(
            "SLA449",
            60,
            Arrays.asList("Tyler", "Singer", "Shaw"),
            Arrays.asList("Zeldin", "Uther")));

        this.activities.add(new Activity(
            "SLA451",
            100,
            Arrays.asList("Tyler", "Singer", "Shaw"),
            Arrays.asList("Zeldin", "Uther", "Richards", "Banks")));
    }
    
    public Activity getActivity(String id) {
    	
    	for(Activity a:activities) {
    		if(a.getCourseId().equals(id))
    			return a;
    	}
    	return null;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}

