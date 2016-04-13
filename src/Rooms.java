import java.io.Serializable;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Room interface, Made it a non interface class so that it could be serializable to implement saving mid-game.
 *
 * Rooms can be accessed via the gameMap list. 
 * Created by dustinrosebery on 4/10/16.
 */
public class Rooms implements Serializable{

    String name;
    String description;
    ArrayList<Items> roomItems = new ArrayList<Items>();

    public static LinkedList<Rooms> gameMap = new LinkedList<Rooms>();

        // add rooms to map

    Rooms(){}

    /**
     * Sets up the rooms into the list. Call when launching game
     */
    static void initRooms(){

        gameMap.addFirst(new Outside());
    }


}
