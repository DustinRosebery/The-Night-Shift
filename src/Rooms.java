import java.io.Serializable;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Room interface, Made it a non interface class so that it could be serializable to implement saving mid-game.
 *
 * Rooms can be accessed via the gameMap list. 
 * Created by dustinrosebery on 4/10/16.
 */
public abstract class Rooms implements Serializable{

    String name = "";
    String description = "";
    ArrayList<Items> roomItems = new ArrayList<Items>();

        // add rooms to map

    Rooms() {}

    //TODO: public void entry()     -- checks room flags and display flag messages

    /**
     * @return name of current room
     */
    public String getName() { return name;}

    /**
     * @return description of room
     */
    public String getDesc() { return description; }

    /**
     * sets the inherited room names
     * @param name of room
     */
    public static void setName (String name) { name = name; }

    /**
     * sets inherited room description
     * @param description of room
     */
    public static void setDesc (String description) { description = description; }


}
