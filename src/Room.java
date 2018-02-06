import java.util.ArrayList;

public class Room {

    public String name;
    public String description;
    public String[] items;

    public Direction[] directions;
    public static ArrayList<Room> allRooms = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getItems() {
        return items;
    }

    public Direction[] getDirections() {
        return directions;
    }

    public static Room getRoomByName(String roomName){
        Room r = new Room();

        for (Room c : Adventure.siebelGame.getRooms()){
            if (c.getName().equals(roomName)){
                return c;
            }
        }
        return null;
    }
}
