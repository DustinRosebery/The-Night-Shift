/**
 * @author Kunal Lanjewar
 * This is Bedroom controller for Dice rolling game.
 * So, you finally made it to the bedroom. Good for you, because there is no one here. 
 * 'take' as much stuff as you can before you get caught!
 * Use "explore" to find stuff.
 * Hint: The bedroom has a popular Samsung equipment that you need to guess,
 * It has a popular robot vacuumclean brand that you need to guess.
 * It has a fitness equipment that you need to guess.
 * It has a popular gaming console that you need to guess.
 * It has something that you hang on the wall.
 * 
 */
public class Bedroom extends Rooms {

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
                } else if (input.toString().contains("samsungtv")) {
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
                } else if (input.toString().contains("fitnessequipment")) {
                    validItem = true;
                    itemIndex = 28;
                    skill = "strength";
                    name = "Fitness Equipment";
                } else {
                    validItem = false;
                    Game.getController().write("There are many thing to steal. Explore the bedroom!");
                }
                if (validItem) {
                    Game.getController().write("Item " + name + "is Valid!");

                    if (!Game.getCurrentCharacter().inventory().getList().contains(Items.itemList.get(itemIndex))) {
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
        else if (handled = args[0].equalsIgnoreCase("explore")){

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
