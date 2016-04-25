/**
 * Created by dustinrosebery on 4/13/16.
 */
public class Basement extends Rooms {

    Basement() {
        name = "Basement";

        description = Saves.loadDescription(name);
    }
}
