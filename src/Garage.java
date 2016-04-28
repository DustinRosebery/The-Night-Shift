
/** Garage. It has a car in it that can be stolen from or used for escape.
 * The car keys are in the kitchen. There is a tool box with a crowbar and 
 * hammer that can be used to smash a car window. Or, you can find an old
 * wire hanger in some boxes to try to pick the lock. There are large outdoor
 * speakers. They are too big to be brought out in a backpack, but you could
 * put them in the trunk of the car. There are bikes, but with flat tires. If
 * you pump them up, you can use a bike as a get away vehicle. Also, in the
 * boxes, there could be old jewelry, cash, or antiques. 
 * 
 * @author Trevor Rosenkilde
 *
 */
public class Garage extends Rooms {
	
	/** Constructor initializing the essentials */
	Garage() {
		roomIndex = 5;
		name = ("Garage");

		description = Saves.loadDescription(name);

		exits = "basement - kitchen";
	}
}
