
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

		exits = "There is a door into the house and another on the other side of the garage, slightly ajar with what look" +
				" stairs that lead downwards.";

		Interpreter interpreter = new Interpreter(input -> {
			boolean handled = false;
			String[] args = input.toString().split(" ");
			boolean toolboxOpen = false;
			boolean windowSmashed = false;
			boolean boxesMoved = false;
			if (handled = args[0].equalsIgnoreCase("open")) {
				if (args.length >= 2) {
					if (input.toString().contains("toolbox")) {
						toolboxOpen = true;
					} else {
						Game.getController().write("What do you want to open? Try looking around.");
					}

				} else {
					Game.getController().write("What do you want to open?");
				}
			}
			else if (handled = args[0].equalsIgnoreCase("take")) {
				boolean validItem = false;
				int itemIndex = -1;
				String skill = "";
				String name = "";
				if (args.length >= 2) {
					if (input.toString().contains("crowbar")) {
						if (toolboxOpen) {
							itemIndex = 29;
							name = "crowbar";
							Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
							Game.getController().write("You take the " + name + " successfully");
						} else {
							Game.getController().write("Try opening the toolbox first.");
						}
					}
					else if (input.toString().contains("GPS")) {
						if (windowSmashed) {
							itemIndex = 17;
							name = "GPS";
							Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
							Game.getController().write("You take the " + name + " successfully");
						}
						else {
							Game.getController().write("The doors are locked. Try getting inside another way.");
						}
					}
					else if (input.toString().contains("cash") || input.toString().contains("money")) {
						if (boxesMoved) {
							itemIndex = 8;
							name = "cash";
							Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
							Game.getController().write("You take the " + name + " successfully");
						}
						else {
							Game.getController().write("I'm not sure where you see that... try moving some stuff around.");
						}
					}
					else {
						validItem = false;
						Game.getController().write("What do you want to grab? Try looking around.");
					}

				} else {
					Game.getController().write("What do you want to grab?");
				}
				if (validItem) {
					if (!Game.getCurrentCharacter().inventory().getList().contains(Items.itemList.get(itemIndex))) {
						if (Game.getCurrentCharacter().skillCheck(skill)) {
							Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
							Game.getController().write("You take the " + name + " successfully");
						} else {
							Game.getController().handleFailure();
						}
					}
				}
			}
			else if (handled = args[0].equalsIgnoreCase("turn")) {
				if (args.length >= 3) {
					if (input.toString().contains("on") && input.toString().contains("lights")) {
						Game.getController().write("You turned on the lights. You now notice a shiny new car, some old " +
								"rusty bikes, a toolbox, some speakers, and other miscellaneous boxes and common garage items.");
					}
				}
			}
			else if (handled = args[0].equalsIgnoreCase("smash")) {
				String skill = "";
				String name = "";
				if (args.length >= 2) {
					if (input.toString().contains("window")) {
						skill = "strength";
						if (Game.getCurrentCharacter().skillCheck(skill)) {
							windowSmashed = true;
							Game.getController().write("You smashed the window successfully");
						}
						else {
							Game.getController().handleFailure();
						}
					}
				}
			}
			else if (handled = args[0].equalsIgnoreCase("move")) {
				String skill = "";
				String name = "";
				if (args.length >= 2) {
					if (input.toString().contains("boxes")) {
						skill = "strength";
						if (Game.getCurrentCharacter().skillCheck(skill)) {
							boxesMoved = true;
							Game.getController().write("You moved the boxes successfully and notice some cash on the ground.");
						}
						else {
							Game.getController().handleFailure();
						}
					}
				}
			}
			return handled;
		});

		setInterpreter(interpreter);


	}

	public void entry(Character character) {
		character.setIndex(roomIndex);
		Game.getController().updateRoom(character);
	}
}
