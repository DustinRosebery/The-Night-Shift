/**
 * @author Connor Nelson
 */

public class Basement extends Rooms {

    public Basement() {
        roomIndex = 1;
        name = "Basement";
        description = Saves.loadDescription(name);
        exits = "Garage - Living Room";
    }

    @Override
    public void entry(Character character) {
        character.setIndex(roomIndex);
        Game.getController().updateRoom(character);

        Interpreter interpreter = new Interpreter(input -> {
            boolean handled = false;
            String[] args = input.toString().split(" ");

            if (handled = args[0].equalsIgnoreCase("take")) {
                boolean validItem = false;
                int itemIndex = -1;
                String skill = "";
                String name = "";

                if (args.length >= 2) {
                    if (args[1].equalsIgnoreCase("vault")) {
                        validItem = true;
                        itemIndex = 20;
                        skill = "strength";
                        name = "rusty vault";
                    } else if (args[1].equalsIgnoreCase("katana")) {
                        validItem = true;
                        itemIndex = 21;
                        skill = "reflex";
                        name = "katana";
                    } else if (args[1].equalsIgnoreCase("keyboard")) {
                        validItem = true;
                        itemIndex = 22;
                        skill = "intelligence";
                        name = "keyboard";
                    } else if (args[1].equalsIgnoreCase("ring")) {
                        validItem = true;
                        itemIndex = 23;
                        skill = "perception";
                        name = "diamond ring";
                    } else if (args[1].equalsIgnoreCase("pot")) {
                        validItem = true;
                        itemIndex = 24;
                        skill = "dexterity";
                        name = "golden pot";
                    } else {
                        validItem = false;
                        Game.getController().write("What do you want to take? Try looking around.");
                    }
                    if (validItem) {
                        if (Game.getCurrentCharacter().inventory().getList().contains(Items.itemList.get(itemIndex))) {
                            if (Game.getCurrentCharacter().skillCheck(skill)) {
                                Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
                                Game.getController().write("You take the " + name + " successfully");
                            } else {
                                Game.getController().handleFailure();
                            }
                        }
                    }
                } else {
                    Game.getController().write("What do you want to grab?");
                }

            } else if (handled = args[0].equalsIgnoreCase("look")) {

            }
            return handled;
        });

        setInterpreter(interpreter);
    }


}
