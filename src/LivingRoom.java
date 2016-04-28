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


        Interpreter interpreter = new Interpreter(input -> {        // How the player picks up an item
            String[] args = ((String) input).split(" ");

            if (args[0].equalsIgnoreCase("take") || args[0].equalsIgnoreCase("get") || args[0].equalsIgnoreCase("grab")) {         //
                if (args.length >= 2) {

                    if (args[1].equalsIgnoreCase("Tablet")) {
                        if (Game.getCurrentCharacter().inventory().getList().contains(Items.itemList.get(1))) {
                            if (Game.getCurrentCharacter().skillCheck("intelligence")) {
                                Game.getCurrentCharacter().addItem(Items.itemList.get(1));
                                Game.getController().write("You take the tablet successfully");
                            } else {
                                Game.getController().write("You failed!");
                            }
                        }
                    }
                }
            }
            return false;

        }, input -> {                                  // basic movement between rooms according to the room map
            boolean handled = false;
            boolean checked = false;
            int outside = 0;
            int basement = 1;
            int livingroom = 2;
            int bedroom = 3;
            int kitchen = 4;
            int garage = 5;

            String[] command = input.toString().split(" "); // splits input into a String array using space as a delimeter
            String[] navWords = {"go", "goto", "sneak"};
            String[] navErrors = {"Where do you want to go?", "How do you plan on getting there?",
                    "You can't go there from here.", "You'll have to try a different route.",
                    "Try another room.", "You can't go that way.", "That seems impossible"};

            int error = navErrors.length;


            for (int wordsIndex = 0; wordsIndex < navWords.length; wordsIndex++) {                 // matches first input command against
                if (handled = (handled || command[0].equalsIgnoreCase(navWords[wordsIndex]))) {    // an array of different movement words

                    for (int cmdIndex = 2; cmdIndex < command.length; cmdIndex++) {
                        int room = character.index();

                        if (command[cmdIndex].equalsIgnoreCase("kitchen") && !checked) {
                            if (room == livingroom || room == garage || room == bedroom) {
                                Game.exitRoom(character);
                                character.setIndex(kitchen);
                                checked = true;
                                Game.enterRoom(character);
                            } else
                                Game.getController().write(navErrors[Dice.rand(error)]);

                        } else if (command[cmdIndex].equalsIgnoreCase("living") || command[cmdIndex].equalsIgnoreCase("livingroom") && !checked) {
                            if (room == basement || room == bedroom || room == kitchen) {
                                Game.exitRoom(character);
                                character.setIndex(livingroom);
                                checked = true;
                                Game.enterRoom(character);
                            } else
                                Game.getController().write(navErrors[Dice.rand(error)]);

                        } else if (command[cmdIndex].equalsIgnoreCase("bed") || command[cmdIndex].equalsIgnoreCase("bedroom") && !checked) {
                            if (room == livingroom || room == kitchen) {
                                Game.exitRoom(character);
                                character.setIndex(bedroom);
                                checked = true;
                                Game.enterRoom(character);
                            } else
                                Game.getController().write(navErrors[Dice.rand(error)]);

                        } else if (command[cmdIndex].equalsIgnoreCase("basement") || command[cmdIndex].equalsIgnoreCase("stairs") && !checked) {
                            if (room == garage || room == livingroom) {
                                Game.exitRoom(character);
                                character.setIndex(basement);
                                checked = true;
                                Game.enterRoom(character);
                            } else
                                Game.getController().write(navErrors[Dice.rand(error)]);

                        } else if (command[cmdIndex].equalsIgnoreCase("garage") && !checked) {
                            if (room == basement || room == kitchen) {
                                Game.exitRoom(character);
                                character.setIndex(5);
                                checked = true;
                                Game.enterRoom(character);
                            } else
                                Game.getController().write(navErrors[Dice.rand(error)]);

                        } else if (command[cmdIndex].equalsIgnoreCase("inside") && !checked) {          // for testing
                            if (room == 0) {
                                Game.exitRoom(character);
                                character.setIndex(2);
                                checked = true;
                                Game.enterRoom(character);
                            } else
                                Game.getController().write("You're already inside...");

                        }
                    }
                }
            }
            if (!checked)
                Game.getController().write(navErrors[Dice.rand(error)]);
            return handled;
        });

        setInterpreter(interpreter);
    } // end of entry

}


