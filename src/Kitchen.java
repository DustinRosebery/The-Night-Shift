/**
 * Created by dustinrosebery on 4/13/16.
 * The Kitchen is extremely neat and seemingly never used, It will be mostly filled with very hard to steal but very expensive
 * items, with a few cheaper and easier items thrown in. It will include a toaster, a fridge that needs the car to steal, a wine collection,
 * a set of fine china, and the car keys will be hidden under a stack of cook books
 */
public class Kitchen extends Rooms {

    Kitchen() {
        roomIndex = 4;

        name = "Kitchen";

        description = Saves.loadDescription((name));


        description = "You are in the Kitchen. This kitchen radiates with potential, it is immediately apparent that " +
        "the owners never cook, each appliance shimmers and shines as you gaze around the room. Some of these items are huge and " +
        "you realize you'll need a car to have a chance to steal them quickly";

        exits = "bedroom - living room - garage";
    }
}
