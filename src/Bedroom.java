/**
 * @author Kunal Lanjewar
 * This is Bedroom controller for Dice rolling game.
 * So, you finally made it to the bedroom. Good for you, because there is no one here. 
 * 'take' as much stuff as you can before you get caught!
 * 
 */
public class Bedroom extends Rooms {

    Bedroom() {
        roomIndex = 3;

        name = "Bedroom";

        description = Saves.loadDescription((name));
        
        exits = "Living room - kitchen";
    }

    @Override
    public void entry(Character character) {
        Interpreter interpreter = new Interpreter(input -> {
            boolean handled = false;
            String[] args = input.toString().split(" ");

            if (handled = args[0].equalsIgnoreCase("take")) {
                boolean validItem = false;
                int itemIndex = -1;
                String skill = "";
                String name = "";

                if (args.length >= 2) {
                    if (args[1].equalsIgnoreCase("roomba")) {
                        validItem = true;
                        itemIndex = 25;
                        skill = "reflex";
                        name = "Roomba Vacuum Cleaner";
                    } else if (args[1].equalsIgnoreCase("samsungtv")) {
                        validItem = true;
                        itemIndex = 26;
                        skill = "intelligence";
                        name = "Samsung Curve TV ";
                    } else if (args[1].equalsIgnoreCase("playstation")) {
                        validItem = true;
                        itemIndex = 27;
                        skill = "dexterity";
                        name = "Playstation4";
                    } else if (args[1].equalsIgnoreCase("painting")) {
                        validItem = true;
                        itemIndex = 28;
                        skill = "perception";
                        name = "Rare Painting";
                    } else if (args[1].equalsIgnoreCase("fitnessequipment")) {
                        validItem = true;
                        itemIndex = 29;
                        skill = "strength";
                        name = "Fitness Equipment";
                    } else {
                        validItem = false;
                        Game.getController().write("There are many thing to steal. Explore the bedroom!");
                    }
                    if (validItem) {
                        if (Game.getCurrentCharacter().inventory().getList().contains(Items.itemList.get(itemIndex))) {
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
            return handled;
        });

        setInterpreter(interpreter);

    }
}
