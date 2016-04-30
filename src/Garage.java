import java.io.Serializable;

/** When you first enter the garage, the lights are off (they need to be turned on). This means
 * that the description of the room is minimal until the lights are turned on. Once the lights are turned
 * on you see that there is a brand new car, some old boxes, a toolbox, some bikes and speakers. You can
 * move the boxes and find cash, you can't move the speakers or the bikes because they are too heavy, and
 * you can open the toolbox to find the crowbar to smash the window to grab the GPS.
 *
 * 
 * @author Trevor Rosenkilde
 *
 */
public class Garage extends Rooms {

	private static class Helper implements Serializable {

		private static final long serialVersionUID = 1l; // required for serialization

		private boolean toolBoxOpen = false;
		private boolean windowSmashed = false;
		private boolean boxesMoved = false;

		public void setToolBoxOpen(boolean toolBoxOpen) {
			this.toolBoxOpen = toolBoxOpen;
		}

		public void setWindowSmashed(boolean windowSmashed) {
			this.windowSmashed = windowSmashed;
		}

		public void setBoxesMoved(boolean boxesMoved) {
			this.boxesMoved = boxesMoved;
		}

		public boolean isToolBoxOpen() {
			return toolBoxOpen;
		}

		public boolean isWindowSmashed() {
			return windowSmashed;
		}

		public boolean isBoxesMoved() {
			return boxesMoved;
		}

	}

	/** Constructor initializing the essentials */
	Garage() {
		roomIndex = 5;
		name = ("Garage");

		description = Saves.loadDescription(name);

		exits = "It's too dark to tell where we can go from here, other than where we came from. ";

		final Helper help = new Helper();

		Interpreter interpreter = new Interpreter((Command)(input) -> {
			boolean handled = false;
			String[] args = input.toString().split(" ");

			if (handled = args[0].equalsIgnoreCase("open")) {

				if (args.length >= 2) {
					if (input.toString().contains("toolbox")) {
						help.setToolBoxOpen(true);
						Game.getController().write("You opened the toolbox.");
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
						if (help.isToolBoxOpen()) {
							itemIndex = 29;
							name = "crowbar";

							if (!Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {
								Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
								Game.getController().write("You take the " + name + " successfully");
							}
							else {
								Game.getController().write("You already have that.");
							}

						} else {
							Game.getController().write("Try opening the toolbox first.");
						}
					}
					else if (input.toString().contains("GPS")) {
						if (help.isWindowSmashed()) {
							itemIndex = 17;
							name = "GPS";

							if (!Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {
								Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
								Game.getController().write("You take the " + name + " successfully");
							}
							else {
								Game.getController().write("You already have that.");
							}
						}
						else {
							Game.getController().write("The doors are locked. Try getting inside another way.");
						}
					}
					else if (input.toString().contains("cash") || input.toString().contains("money")) {
						if (help.isBoxesMoved()) {
							itemIndex = 8;
							name = "cash";

							if (!Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {
								Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
								Game.getController().write("You take the " + name + " successfully");
							}
							else {
								Game.getController().write("You already have that.");
							}
						}
						else {
							Game.getController().write("I'm not sure where you see that... try moving some stuff around.");
						}
					}
					else if (input.toString().contains("speakers") || input.toString().contains("bikes")) {
						Game.getController().write("They look way too heavy too get out of here...");
					}
					else {
						validItem = false;
						Game.getController().write("What do you want to grab? Try looking around.");
					}

				} else {
					Game.getController().write("What do you want to grab?");
				}
				if (validItem) {
					if (!Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {
						if (Game.getCurrentCharacter().skillCheck(skill)) {
							Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
							Game.getController().write("You take the " + name + " successfully");
						} else {
							Game.getController().handleFailure();
						}
					}
				}
			}
			else if (handled = (args[0].equalsIgnoreCase("turn") || args[0].equalsIgnoreCase("switch"))) {
				if (args.length >= 2) {
					if (input.toString().contains("lights")) {
						description = "You turned on the lights. You now notice a shiny new car, some old rusty bikes, a toolbox, some speakers, and other" +
								" miscellaneous boxes and common garage items. ";
						exits = "The door into the kitchen is on one side of the garage, and a door with what looks like" +
								" stairs leading down is on the other side. ";
						Game.getController().updateRoom(Game.getCurrentCharacter());
						Game.getController().write(exits);
					}
				}
			}
			else if (handled = args[0].equalsIgnoreCase("smash")) {
				String skill = "";
				String name = "";
				int itemIndex = 29;
				if (args.length >= 2) {
					if (input.toString().contains("window")) {

						if (Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {
							skill = "strength";
							if (Game.getCurrentCharacter().skillCheck(skill)) {
								help.setWindowSmashed(true);
								Game.getController().write("You smashed the window successfully and notice a brand new" +
										" GPS sitting on the dash board.");
							}
							else {
								Game.getController().handleFailure();
							}
						}
						else {
							Game.getController().write("You don't have anything to do that with...");
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
							help.setBoxesMoved(true);
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
