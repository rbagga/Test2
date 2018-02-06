import com.google.gson.Gson;

import java.util.List;
import java.util.Scanner;

public class Adventure{

    public static Gson gson = new Gson();
    Scanner scan = new Scanner(System.in);

    public static List<String> gameList = Data.getJsonFilesAsList();
    public static String gameData = Data.getFileContentsAsString(gameList.get(0));
    public static GameWorld siebelGame = gson.fromJson(gameData, GameWorld.class);

    public static Room currentRoom = Room.getRoomByName(siebelGame.getStartingRoom());
    public static Room endingRoom = Room.getRoomByName(siebelGame.getEndingRoom());

    public static boolean gameOver = false;

    public static void main(String[] args) {

        while (!gameOver){
            System.out.println(currentRoom.description);
        }



        }
    }
