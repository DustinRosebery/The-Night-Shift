import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;




/**
 * Saves uses serialization and IO streams to write/read characters to/from file
 * @author dustinrosebery, Connor Nelson
 *
 */
public final class Saves {

    private static final String PATH = "data/rooms.txt";


    /**
     * Saves is a static class--it should not be instantiated by anyone
     */
    private Saves() {};

    /**
     * load room description for input room name
     * @param room
     * @return description string
     */
    public static String loadDescription(String room) {

        String ROOM = null;

        if ((room.equals("Outside"))== true) {
            ROOM = "data/outside.txt";
        }

        else if ((room.equals("Kitchen"))== true) {
            ROOM = "data/kitchen.txt";
        }

        else if ((room.equals("Living Room"))== true) {
            ROOM = "data/livingroom.txt";
        }

        else if ((room.equals("Bedroom"))== true) {
            ROOM = "data/bedroom.txt";
        }

        else if ((room.equals("Basement"))== true) {
            ROOM = "data/basement.txt";
        }

        else if ((room.equals("Garage"))== true) {
            ROOM = "data/garage.txt";
        }

        String line = null;

        try {
            Scanner scan = new Scanner(new File(ROOM));

            line = scan.nextLine();
            return line;

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return line;
    }
    //TODO: public static loadDescription(String "room")

    /**
     * Saves created characters to a .ser file in the data folder.
     * @param character - Character object to be written to file
     */
    public static void writeCharacter(Character character) throws IOException {
        String saveName = character.name() + ".ser";

        FileOutputStream fileOutputStream = new FileOutputStream(PATH + saveName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(character);

        objectOutputStream.close();
        fileOutputStream.close();
    }
    
    /**
     * Reads the saved character from a .ser file in the data folder.
     * @throws IOException The character was not found
     * @throws ClassNotFoundException The character was not found
     * @param name The name of the character
     * @return Character object of the saved character upon successful lookup
     */
    static Character readCharacter(String name) throws IOException, ClassNotFoundException {
        name += ".ser";

        FileInputStream fileInputStream = new FileInputStream(PATH + name);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Character result = (Character) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        return result;
    }

    /**
     * Finds all of the saved characters with a .ser file extension in the data folder.
     * @return A list of the available characters
     */
    static ArrayList<String> getCharacters() {
        ArrayList<String> result = new ArrayList<String>();
        File dataFolder = new File(PATH);
        for (File file : dataFolder.listFiles())
            if (file.isFile() && file.getPath().endsWith(".ser"))
                result.add(file.getPath().replace(PATH, "").replace(".ser", ""));

        return result;
    }
    
    /**
     * Saves the LeaderBoard static class ArrayList containing all recorded scores
     * @param savedScores leader
     */
    static void writeScores(ArrayList<LeaderBoard> savedScores){

            String saveName = "leaderboard.ser";
            try {
                FileOutputStream fos = new FileOutputStream(saveName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(savedScores);
                oos.close();
            }
            catch (FileNotFoundException e) {
                    e.printStackTrace();
            }
            catch (IOException e) {
                    e.printStackTrace();
            }
        }

    /**
     * reads the savedScores list from the file leaderboard.ser
     * @return
     */
    static ArrayList<LeaderBoard> readScores(){

        ArrayList<LeaderBoard> result = new ArrayList<LeaderBoard>();

        try{
            FileInputStream fis = new FileInputStream("leaderboard.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (ArrayList<LeaderBoard>) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e) {
        //e.printStackTrace();
        }
        catch (IOException e) {
        //e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
        //e.printStackTrace();
        }

        return result;
    }
}

