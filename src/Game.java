import com.sun.tools.internal.jxc.ap.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {


    GameWorld world;
    Room currentRoom = Room.getRoomByName(world.getStartingRoomName(), world);
    ArrayList<String> yourItems;

    public String playNext(){

        //checking if current room is starting or ending room.
        System.out.println(currentRoom.getDescription());

        if (currentRoom.getName() == world.getEndingRoomName()){
            return Constants.WIN_GAME;
        } else if (currentRoom.getName() == world.getStartingRoomName()){
            return Constants.START_GAME;
        }

        System.out.println(currentRoom.listItems());

        Scanner scan = new Scanner(System.in);
        System.out.println("enter a command: ");
        String [] userInput = scan.nextLine().split(" ");
        return doCommand(userInput);
    }

    public String doCommand(String [] command){

        //if user wants to leave
        if (command[0].toLowerCase().equals("quit") || command[0].toLowerCase().equals("exit")) {
            return Constants.EXITED_GAME;

        //if user wants to go in a certain direction
        } else if (command[0].toLowerCase().equals("go")) {
            if (command.length > 1){
                return goInDirection(command[1]);
            } else {
                return Constants.INVALID_COMMAND;
            }

        //if user wants to take an item.
        } else if (command[0].toLowerCase().equals("take")) {
            if (command.length > 1){
                return takeItem(command[1]);
            } else {
                return Constants.INVALID_COMMAND;
            }

        //if user wants to drop an item.
        } else if (command[0].toLowerCase().equals("drop")) {
            if (command.length > 1){
                return dropItem(command[1]);
            } else {
                return Constants.INVALID_COMMAND;
            }

        //if user wants to list items.
        } else if (command[0].toLowerCase().equals("list")) {
            return listItems(yourItems);

        //not a legal command
        } else {

            String errorMessage = Constants.INVALID_COMMAND;
            for (String word : command) {
                errorMessage += (word + " ");
            }
            return errorMessage;
        }
    }

    //-------- HELPER FUNCTIONS BELOW

    /**
     *
     * @param direction the direction to go in.
     * @return whether the move was valid, and if you are at the beginning/end as string.
     */
    public String goInDirection(String direction){

        for (Direction d : currentRoom.getDirections()) {
            if (direction.equalsIgnoreCase(d.getDirectionName())) {
                currentRoom = Room.getRoomByName(d.getRoom(), world);
                if (currentRoom.equals(Room.getRoomByName(world.getEndingRoomName(), world))) {
                    return Constants.WIN_GAME;
                } else if (currentRoom.equals(Room.getRoomByName(world.getStartingRoomName(), world))) {
                    return Constants.START_GAME;
                }

                return Constants.VALID_MOVE;
            }
        }

        return (Constants.INVALID_MOVE + direction + ".");
    }

    /**
     *
     * @param item item to take
     * @return whether the item was taken successfully or not as string.
     */
    public String takeItem(String item){
        if (Arrays.asList(currentRoom.getItems()).contains(item.toLowerCase())) {
            for (String itemsInRoom : currentRoom.getItems()) {
                if (itemsInRoom.equalsIgnoreCase(item)) {
                    if (!(yourItems.contains(itemsInRoom))) { //you can't pick up an item you already have
                            yourItems.add(itemsInRoom);
                    } else {
                        return Constants.DUP_ITEM;
                    }
                }
            }
        } else {
            return Constants.NO_ITEM;
        }

        return Constants.VALID_TAKE + item + ".";

    }

    /**
     *
     * @param item item to drop
     * @return whether item was dropped
     */
    public String dropItem(String item){
        if (yourItems.contains(item)) {
            yourItems.remove(item);
            return Constants.VALID_DROP + item + ".";
        } else {
            return Constants.INVALID_DROP;
        }
    }

    /**
     *
     * @param items array list of items
     * @return list of items as string.
     */
    public String listItems(ArrayList<String> items){

        String yourItemsAsList = "\n";
        for (String i : yourItems) {
            yourItemsAsList += i + "\n";
        }
        return yourItemsAsList;
    }
}

