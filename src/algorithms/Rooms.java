package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Rooms {
    Room[] rooms;

    public Rooms() {
        rooms = new Room[]{
            new Room("Slater 003", 45),
            new Room("Roman 216", 30),
            new Room("Loft 206", 75),
            new Room("Roman 201", 50),
            new Room("Loft 310", 108),
            new Room("Beach 201", 60),
            new Room("Beach 301", 75),
            new Room("Logos 325", 450),
            new Room("Frank 119", 60)
        };
    }
    
    /**
     * 
     * @param capacity
     * @return
     */
    public Room[] getRooms(int capacity) {
        List<Room> filteredRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (room.capacity > capacity) {
                filteredRooms.add(room);
            }
        }

        return filteredRooms.toArray(new Room[0]);
    }
    /**
     * 
     * @return
     */
    public Room[] getRooms() {
    	return getRooms(0);
    }
    /**
     * 
     * @param name
     * @return
     */
    public Room getRoom(String name) {
        for (Room room : rooms) {
            if (room.name.equals(name)) {
                return room;
            }
        }
        return null; 
    }
    
}
