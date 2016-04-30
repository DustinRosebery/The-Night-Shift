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

                    if (input.toString().contains("tablet")) {
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
                    else if (input.toString().contains("tv")) {
                        validItem = true;
                        rollRequired = true;
                        itemIndex = 2;
                        skill = "strength";
                        name = "tv";
                        Game.getController().write("Getting this down is gonna be tough.");
                    }
                    else if (input.toString().contains("pen")) {
                        validItem = true;
                        rollRequired = false;
                        itemIndex = 10;
                        name = "pen";
                    }
                    else if (input.toString().contains("blu") || input.toString().contains("player")) {
                        validItem = true;
                        rollRequired = true;
                        itemIndex = 4;
                        skill = "dexterity";
                        name = "blu-ray player";
                        Game.getController().write("As you reach for the blu-player player, a cord gets caught and starts to tip the entertainment center");
                    }
                    else if (input.toString().contains("curtains")) {
                        validItem = true;
                        rollRequired = true;
                        itemIndex = 30;
                        skill = "strength";
                        name = "curtains";
                        Game.getController().write("Getting these down without making any noise is gonna take some muscle.");
                    }

                    if (validItem) {
                        if (!!Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {

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
                            Game.getController().write("Both the sofa and the love-seat look to be in perfect condition. " +
                                    "Quality black leather is riveted together with copper buttons holding it all in place.");
                        }
                    }
                    else if (handled = (input.toString().contains("art"))) {

                        if (Dice.rand(17) <= Game.getCurrentCharacter().intelligence() - 1) {
                            Game.getController().write("Looking closely you see the faintest signature on the bottom right corner of " +
                            "one of these paintings. Pablo Picasso, who would have guessed? Aside from their beauty you know that these must " +
                            "be extremely valuable.");
                        }
                        else {
                            Game.getController().write("Nothing strikes you as special about these paintings, tired, old and full of messy " +
                            "lines. They would probably be more trouble than they are worth.");
                        }
                    }
                    else if (handled = (input.toString().contains("entertainment") || input.toString().contains("center"))) {

                        if (Dice.rand(17) <= Game.getCurrentCharacter().perception() - 1) {
                            Game.getController().write("Inside the large metal and glass entertainment center you see a sony blu-ray player, and a " +
                            "staggering amount of messy cables and wires going god knows where... but among the clutter you think you see a shiny gold " +
                            "end of a fancy pen.");
                        }
                        else {
                            Game.getController().write("Inside the large metal and glass entertainment center you see a sony blu-ray player, and a " +
                                    "staggering amount of messy cables and wires going god knows where...");
                        }
                    }
                    else if (handled = (input.toString().contains("coffee") || input.toString().contains("table"))) {

                        Game.getController().write("On top of the beautiful coffee table you see a nice new tablet partially covered by some sports " +
                                "illustrated magazines.");
                    }
                    else if (handled = (input.toString().contains("curtains"))) {

                        Game.getController().write("These are the most beautiful and luxurious curtains you have ever seen, they hang all the way from " +
                                "the ceiling to the floor and are woven into modern shapes and patterns from a mysterious shimmering material.");
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


