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

    int roomIndex;                  // index of your room in the game map.
    int state;                      // keep track of the state of your room.
    String name = "";
    String description = "";
    String exits = "";              // shows the possible room exits to be written to the GUI (historyField)

    public int getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExits() {
        return exits;
    }

    public void setExits(String exits) {
        this.exits = exits;
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    Interpreter interpreter;
    static String[] lookCommands;

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

    /**
     * Room states:
     *
     * 0    normal
     * 1    notices open window
     */

    Rooms() {}


    /**
     * Runs every time a character enters your room
     * @param character
     */
    public void entry(Character character) {
        int state = 0;
    }


    /**
     * -- You can write mutiple room descriptions to .txt files and give them associated states
     *    and call them using this function --
     *
     * set the room description based on the room state
     * @param state
     */
    public void loadDescription( int state ) {
        description = Saves.loadDescription(roomIndex, state);
        // load description based on the state of your room
    }


    /**
     * @return roomIndex
     */
    public int index() { return roomIndex; }

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
