/**
 * Created by dustinrosebery on 4/13/16.
 * The Kitchen is extremely neat and seemingly never used, It will be mostly filled with very hard to steal but very expensive
 * items, with a few cheaper and easier items thrown in. It will include a toaster, a fridge that needs the car to steal, a wine collection,
 * a set of fine china, and the car keys will be hidden under a stack of cook books
 */
public class Kitchen extends Rooms {

    Kitchen() {
        name = "Kitchen";

        description = Saves.loadDescription((name));

    }
}
