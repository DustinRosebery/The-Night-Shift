/**
 * Created by dustinrosebery on 4/13/16.
 */
public class Bedroom extends Rooms {

    Bedroom() {
        roomIndex = 3;

        name = "Bedroom";

        description = Saves.loadDescription((name));

        description = "you are in the bedroom";

        exits = "Living room - kitchen";
    }
}
