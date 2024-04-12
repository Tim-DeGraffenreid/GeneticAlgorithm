package algorithms;
import org.json.JSONArray;
import org.json.JSONObject;

public class GA {
	public JSONObject options = new JSONObject();;
	
	public GA() {
		setOptions();
	}
	
	private void setOptions() {
		
		
        JSONArray activities = new JSONArray();
     
        activities.put(new JSONObject()
        	.put("courseId", "SLA100A")
            .put("expectedEnrollment", 50)
            .put("preferredFacilitators", new JSONArray().put("Glen").put("Lock").put("Banks").put("Zeldin"))
            .put("otherFacilitators", new JSONArray().put("Numen").put("Richards")));

        activities.put(new JSONObject()
            	.put("courseId", "SLA100B")
                .put("expectedEnrollment", 50)
                .put("preferredFacilitators", new JSONArray().put("Glen").put("Lock").put("Banks").put("Zeldin"))
                .put("otherFacilitators", new JSONArray().put("Numen").put("Richards")));
        //.put("courseId", new JSONArray().put("SLA191A").put("SLA191B"))
        activities.put(new JSONObject()
            .put("courseId", "SLA191A")
            .put("expectedEnrollment", 50)
            .put("preferredFacilitators", new JSONArray().put("Glen").put("Lock").put("Banks").put("Zeldin"))
            .put("otherFacilitators", new JSONArray().put("Numen").put("Richards")));

        activities.put(new JSONObject()
                .put("courseId", "SLA191B")
                .put("expectedEnrollment", 50)
                .put("preferredFacilitators", new JSONArray().put("Glen").put("Lock").put("Banks").put("Zeldin"))
                .put("otherFacilitators", new JSONArray().put("Numen").put("Richards")));
        // Repeat the above pattern for the remaining activities
        activities.put(new JSONObject()
            .put("courseId", "SLA201")
            .put("expectedEnrollment", 50)
            .put("preferredFacilitators", new JSONArray().put("Glen").put("Banks").put("Zeldin").put("Shaw"))
            .put("otherFacilitators", new JSONArray().put("Numen").put("Richards").put("Singer")));

        activities.put(new JSONObject()
            .put("courseId", "SLA291")
            .put("expectedEnrollment", 50)
            .put("preferredFacilitators", new JSONArray().put("Lock").put("Banks").put("Zeldin").put("Singer"))
            .put("otherFacilitators", new JSONArray().put("Numen").put("Richards").put("Shaw").put("Tyler")));

        activities.put(new JSONObject()
            .put("courseId", "SLA303")
            .put("expectedEnrollment", 60)
            .put("preferredFacilitators", new JSONArray().put("Glen").put("Zeldin").put("Banks"))
            .put("otherFacilitators", new JSONArray().put("Numen").put("Singer").put("Shaw")));

        activities.put(new JSONObject()
            .put("courseId", "SLA304")
            .put("expectedEnrollment", 25)
            .put("preferredFacilitators", new JSONArray().put("Glen").put("Banks").put("Tyler"))
            .put("otherFacilitators", new JSONArray().put("Numen").put("Singer").put("Shaw").put("Richards").put("Uther").put("Zeldin")));

        activities.put(new JSONObject()
            .put("courseId", "SLA394")
            .put("expectedEnrollment", 20)
            .put("preferredFacilitators", new JSONArray().put("Tyler").put("Singer"))
            .put("otherFacilitators", new JSONArray().put("Richards").put("Zeldin")));

        activities.put(new JSONObject()
            .put("courseId", "SLA449")
            .put("expectedEnrollment", 60)
            .put("preferredFacilitators", new JSONArray().put("Tyler").put("Singer").put("Shaw"))
            .put("otherFacilitators", new JSONArray().put("Zeldin").put("Uther")));

        activities.put(new JSONObject()
            .put("courseId", "SLA451")
            .put("expectedEnrollment", 100)
            .put("preferredFacilitators", new JSONArray().put("Tyler").put("Singer").put("Shaw"))
            .put("otherFacilitators", new JSONArray().put("Zeldin").put("Uther").put("Richards").put("Banks")));

		
		
		JSONArray facilitators = new JSONArray();

		// Adding each facilitator's name to the JSONArray
		facilitators.put("Lock");
		facilitators.put("Glen");
		facilitators.put("Banks");
		facilitators.put("Richards");
		facilitators.put("Shaw");
		facilitators.put("Singer");
		facilitators.put("Uther");
		facilitators.put("Tyler");
		facilitators.put("Numen");
		facilitators.put("Zeldin");
		
		JSONArray rooms = new JSONArray();

		// Adding each room as a JSONObject to the rooms JSONArray
		rooms.put(new JSONObject().put("room", "Slater 003").put("capacity", 45));
		rooms.put(new JSONObject().put("room", "Roman 216").put("capacity", 30));
		rooms.put(new JSONObject().put("room", "Loft 206").put("capacity", 75));
		rooms.put(new JSONObject().put("room", "Roman 201").put("capacity", 50));
		rooms.put(new JSONObject().put("room", "Loft 310").put("capacity", 108));
		rooms.put(new JSONObject().put("room", "Beach 201").put("capacity", 60));
		rooms.put(new JSONObject().put("room", "Beach 301").put("capacity", 75));
		rooms.put(new JSONObject().put("room", "Logos 325").put("capacity", 450));
		rooms.put(new JSONObject().put("room", "Frank 119").put("capacity", 60));
		
        JSONArray times = new JSONArray();
        times.put("10 AM");
        times.put("11 AM");
        times.put("12 PM");
        times.put("1 PM");
        times.put("2 PM");
        times.put("3 PM");
        
        
        options.put("activities", activities);
        options.put("rooms", rooms);
        options.put("times", times);
        options.put("facilitators", facilitators);
        
	}
	
	public static void main(String[] args) {
		GA ga = new GA();
		System.out.println(ga.options.get("rooms"));
	}
}
