/**
 * @author Kunal Lanjewar
 * This is Bedroom controller for Dice rolling game.
 * So, you finally made it to the bedroom. Good for you, because there is no one here. 
 * 'take' as much stuff as you can before you get caught!
 *
 * There is nice vacuum cleaner in the corner, a stuff for entertainment, there is also a rare painting on the wall,
 * the owner is fitness contious so there is a fitness equipment aswell.
 * 
 */
import java.io.Serializable;

public class Bedroom extends Rooms {
	
	/**  Initializing the essentials Bedroom constructor */

    public Bedroom() {
    roomIndex = 3;

    name = "Bedroom";

    description = Saves.loadDescription((name));
    
    exits = "Living room - kitchen";

    Interpreter interpreter = new Interpreter((Command)(input) -> {
        boolean handled = false;
        String[] args = input.toString().split(" ");

        if (handled = args[0].equalsIgnoreCase("take")) {
            boolean validItem = false;
            int itemIndex = -1;
            String skill = "";
            String name = "";

            if (args.length >= 2) {
                if (input.toString().contains("roomba")) {
                    validItem = true;
                    itemIndex = 24;
                    skill = "reflex";
                    name = "Roomba Vacuum Cleaner";
                } else if (input.toString().contains("tv")) {
                    validItem = true;
                    itemIndex = 25;
                    skill = "intelligence";
                    name = "Samsung Curve TV ";
                } else if (input.toString().contains("playstation")) {
                    validItem = true;
                    itemIndex = 26;
                    skill = "dexterity";
                    name = "Playstation4";
                } else if (input.toString().contains("painting")) {
                    validItem = true;
                    itemIndex = 27;
                    skill = "perception";
                    name = "Rare Painting";
                } else if (input.toString().contains("treadmill")) {
                    validItem = true;
                    itemIndex = 28;
                    skill = "strength";
                    name = "Fitness Equipment";
                } else {
                    validItem = false;
                    Game.getController().write("There are many thing to steal. Explore the bedroom!");
                }
                if (validItem) {
                    Game.getController().write("Item " + name + " is Valid!");

                    if (!Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {
                        if (Game.getCurrentCharacter().skillCheck(skill)) {
                            Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
                            Game.getController().write("Nice job! You took " + name + " successfully");
                        } else {
                            Game.getController().handleFailure();
                        }
                    }
                }
            } else {
                Game.getController().write("So, What do you want to steal?");
            }
        }
        else if ((handled = args[0].equalsIgnoreCase("explore")) || (handled = args[0].equalsIgnoreCase("find")) ||
                (handled = args[0].equalsIgnoreCase("search")) || (handled = args[0].equalsIgnoreCase("look"))){

            if (input.toString().contains("clean") || input.toString().contains("vacuum") || input.toString().contains("roomba") ) {
                Game.getController().write("Looks like you found a fancy robot vacuum cleaner. take roomba");
            } else if ((input.toString().contains("fun")) || (input.toString().contains("game")) ||
                    (input.toString().contains("playstation"))) {
                Game.getController().write("Looks like you found a playstation. take playstation");
            } else if ((input.toString().contains("entertainment")) || (input.toString().contains("tv"))) {
                Game.getController().write("Looks like you found a tv. take tv");
            } else if ((input.toString().contains("wall")) || (input.toString().contains("painting"))) {
                Game.getController().write("Looks like you found a painting. take painting");
            } else if ((input.toString().contains("fitness")) || (input.toString().contains("gym"))) {
                Game.getController().write("Looks like you found a treadmill. take treadmill");
            }

        }
        return handled;
    });

    setInterpreter(interpreter);

    }

    @Override
    public void entry(Character character) {
        character.setIndex(roomIndex);
        Game.getController().updateRoom(character);
    }
}
