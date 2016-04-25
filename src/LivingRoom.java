/**
 * Created by dustinrosebery on 4/13/16.
 */
public class LivingRoom extends Rooms {

    LivingRoom() {
        name = ("Living Room");

        description = Saves.loadDescription((name));
                /*("This room is by far the largest in the house with a chic, yet comfy modern decor. Two large windows with " +
                        "curtains that hang to the floor line the wall next to the front door. The other wall houses A gigantic flat screen tv " +
                        " hanging up over " +
                        "a fancy entertainment center across from two gigantic leather couches and a large black rectangular coffee table. " +
                        "Some tasteful artwork hangs on the far wall flanking what looks like a closet door. The stone tiled floors making moving " +
                        "around quietly a bit more difficult.");*/

        exits = "You notice a door cracked open with some stairs leading down to what you assume is the basement, another door that you think " +
                "you can hear someone snoring behind, and the archway leading into the kitchen.";
    }
}
