/**
 * Created by dustinrosebery on 4/13/16.
 */
public class LivingRoom extends Rooms {



    LivingRoom() {
        roomIndex = 2; 

        name = ("Living Room");

        description = Saves.loadDescription((name));

        exits = "You notice a door cracked open with some stairs leading down to what you assume is the basement, another door that you think " +
                "you can hear someone snoring behind, and the archway leading into the kitchen.";

        Interpreter interpreter = new Interpreter((Command)(input) -> {        // How the player picks up an item
            String[] args = ((String) input).split(" ");
            boolean handled = false;

            if (handled = (args[0].equalsIgnoreCase("take") || args[0].equalsIgnoreCase("get") || args[0].equalsIgnoreCase("grab"))) {
                boolean validItem = false;
                boolean rollRequired = false;
                int itemIndex = -1;
                String skill = "";
                String name = "";

                if (args.length >= 2) {

                    if (input.toString().contains("Tablet")) {
                        validItem = true;
                        rollRequired = true;
                        itemIndex = 0;
                        skill = "reflex";
                        name = "tablet";
                    }
                    else if (input.toString().contains("money") || input.toString().contains("cash")) {
                        validItem = true;
                        rollRequired = false;
                        itemIndex = 8;
                        skill = "";
                        name = "wad of cash";
                    }

                    if (validItem) {
                        if (!Game.getCurrentCharacter().inventory().getList().contains(Items.itemList.get(1))) {

                            if (rollRequired && Game.getCurrentCharacter().skillCheck(skill)) {
                                Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
                                Game.getController().write("You take the " + name + " successfully");
                            }
                            else if (rollRequired) {
                                    Game.getController().handleFailure();
                            }
                            else {
                                Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
                            }
                        }
                    }
                }
            }
            return handled;

        }, (Command)(input) -> {
            String[] args = ((String) input).split(" ");
            boolean handled = false;

            if (handled = (args[0].equalsIgnoreCase("look") || args[0].equalsIgnoreCase("search"))) {


                if (args.length >= 2) {

                    if (handled = (input.toString().contains("couch"))) {
                        if (Dice.rand(17) <= Game.getCurrentCharacter().perception() - 1) {
                            Game.getController().write("Both the sofa and the loveseat look to be in perfect condition. " +
                                    "Quality black leather is riveted together with copper buttons holding it all in place. Out of the " +
                                    "corner of your eyes you notice what looks like the edge of some type of money sticking up from the back " +
                                    "of one of the cushions.");
                        } else {
                            Game.getController().write("Both the sofa and the loveseat look to be in perfect condition. " +
                                    "Quality black leather is riveted together with copper buttons holding it all in place.");
                        }
                    }
                }
            }
            return handled;
        });

        setInterpreter(interpreter);

    }

    /**
     * Runs when the living room is entered and gives the room control of the interpreter
     * @param character
     */
    public void entry(Character character) {

        state = 0;
        description = Saves.loadDescription(name);

        character.setIndex(roomIndex);
        Game.getController().updateRoom(character);



    } // end of entry

}


