import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Saves uses serialization and IO streams to write/read characters to/from the default workspace
 * @author dustinrosebery
 *
 */
abstract class Saves {
	
	/**
	 * Saves created characters to a .ser file in the default project workspace
	 * @param saveChar - Character object to be written to file
	 */
	static void writeChar(Character saveChar){
		
		String saveName = saveChar.getName() + ".ser";
		try {
			FileOutputStream fos = new FileOutputStream(saveName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveChar);
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
	 * Reads the saved character from a .ser file in the default workspace.
	 * @param charName of character to be retrieved
	 * @return a Character object of the saved character upon successful lookup,
	 * or a null Character upon failure.
	 */
	static Character readChar(String charName){
		
		charName = charName + ".ser";
		
		Character result = new Character();
		
		try{
			FileInputStream fis = new FileInputStream(charName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			result = (Character) ois.readObject();
			ois.close();
		}
		catch (FileNotFoundException e) {
		//e.printStackTrace();
		result = new Character();
		}
		catch (IOException e) {
		//e.printStackTrace();
		result = new Character();
		}
		catch (ClassNotFoundException e) {
		//e.printStackTrace();
		result = new Character();
		}
		
		return result;
	}
	
/**
 * Saves the LeaderBoard static class ArrayList containing all recorded scores
 * @param savedScores
 * @param name
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

