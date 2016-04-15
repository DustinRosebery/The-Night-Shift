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
    String exits = ""; // shows the possible room exits to be written to the GUI (historyField)

    /**
     * Room Map:
     *
     * 0    outside
     * 1    basement
     * 2    livingroom
     * 3    bedroom
     * 4    kitchen
     * 5    garage
     */

    Rooms() {}



    /**
     * @return name of current room
     */
    public String getName() { return name;}

    /**
     * @return description of room
     */
    public String getDesc() { return description; }

    /**
     * @return room exits
     */
    public String exits() { return exits; }

}
