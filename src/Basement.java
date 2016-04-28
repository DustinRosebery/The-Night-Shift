/**
 * Created by dustinrosebery on 4/13/16.
 */
public class Basement extends Rooms {

    Basement() {
        roomIndex = 1;

        name = "Basement";
        
        description = Saves.loadDescription(name);

        description = "You are in the basement";

        exits = "Garage - Living Room";

    }
}
