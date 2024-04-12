package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String courseId;
    private int expectedEnrollment;
    private List<String> preferredFacilitators;
    private List<String> otherFacilitators;

    public Activity(String courseId, int expectedEnrollment, List<String> preferredFacilitators, List<String> otherFacilitators) {
        this.courseId = courseId;
        this.expectedEnrollment = expectedEnrollment;
        this.preferredFacilitators = new ArrayList<>(preferredFacilitators);
        this.otherFacilitators = new ArrayList<>(otherFacilitators);
    }
    
    public boolean isPreferredFacilitator(String facilitator) {
    	
    	for(String fac: preferredFacilitators) {
    		if(fac.equals(facilitator))
    			return true;
    	}
    	
    	return false;
    }
    
    public boolean isOtherFacilitator(String facilitator) {
    	
    	for(String fac: preferredFacilitators) {
    		if(fac.equals(facilitator))
    			return true;
    	}
    	
    	return false;
    }
    

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getExpectedEnrollment() {
        return expectedEnrollment;
    }

    public void setExpectedEnrollment(int expectedEnrollment) {
        this.expectedEnrollment = expectedEnrollment;
    }

    public List<String> getPreferredFacilitators() {
        return preferredFacilitators;
    }

    public void setPreferredFacilitators(List<String> preferredFacilitators) {
        this.preferredFacilitators = preferredFacilitators;
    }

    public List<String> getOtherFacilitators() {
        return otherFacilitators;
    }

    public void setOtherFacilitators(List<String> otherFacilitators) {
        this.otherFacilitators = otherFacilitators;
    }
}

