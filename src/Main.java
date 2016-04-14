import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author dustinrosebery
 *
 */
public class Main {

	/**
	 * populates ItemList -- Loads LeaderBoard info -- Initialize Rooms
	 * @param
	 */
	public static void initGame(){
		Saves.readScores();

		//control
	}
	
	/**
	 * System out of menu options
	 */
	public static void printMenu(){
		System.out.println("\n\n\tMain Menu" +
						   "\n**************************" +
						   "\n1. Create a new character" +
						   "\n2. Load a saved Character" +
						   "\n3. View leaderboard" +
						   "\n4. Show help menu" +
						   "\n5. quit");
	}
	
	/**
	 * Displays Main Menu options and detects input
	 */
	public static void mainMenu() {
		printMenu();
		String choice = "";
		String name;
		
		System.out.println("\n\nWhat would you like to do?\t");
		Scanner scan = new Scanner(System.in);
		Character myChar = null;
		
		choice = scan.next();
		
		switch (choice) {
		
		/**
		 * Sets the players new characters default stats and saves it to file -- branch to GameMenu
		 */
		case ("1") : 	// create a new character		
			System.out.println("Enter your characters name\t");
			name = scan.next();
			
			if (name.compareTo("") == 0){
				System.out.println("invalid name.");
				mainMenu();
			}
			else{
				myChar = new Character(name);
				try {
					Saves.writeCharacter(myChar);
				} catch (IOException e) {
					e.printStackTrace();
				}
				LeaderBoard.addScore(myChar.score());
				System.out.println( "\n**************************"
									+ "\n" + name + " created and saved!" 
									+ "\n**************************");
				GameMenu.start();
			}
			
		/**
		 * Loads a character by input name -- branches to GameMenu
		 */
		case ("2") :	// load character
			System.out.println("Which character would you like to play?");
			name = scan.next();
			try {
				myChar = Saves.readCharacter(name);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (myChar.name() == ""){
				System.out.println(name + " was not found");
				mainMenu();
			}
			else{
				System.out.println( "\n**************************"
									+ "\n" + name + " loaded successfully!" 
									+ "\n**************************");
				GameMenu.start();
			}
			
		/**
		 * displays LeaderBoard Standings -- not implemented
		 */
		case ("3") :	// View leaderboard
			System.out.println("\nLeaderBoard not yet implemented\n");
		
		/**
		 * exits the program
		 */
		case ("5") :	// quit program
			break;
			
		/**
		 * invalid input -- branch mainMenu
		 */
		default :	// check for valid input
			System.out.println("please enter the number of your choice.");
			mainMenu();
			
		}// end switch	
		scan.close();
	}// end mainMenu
	
	/**
	 * Displays Character attributes
	 * @param myChar object to display
	 */
	public static void printCharacter(Character myChar){
		
		System.out.println("\nCharacter Information." +
							"\n**************************");
		System.out.println("Name: " + myChar.name() +
						 "\n--------------------------" +
						   "\nStrength:\t\t" + myChar.strength() +
						   "\nReflex:\t\t\t" + myChar.reflex() +
						   "\nIntelligence:\t\t" + myChar.intelligence() +
						   "\nPerception:\t\t" + myChar.perception() +
						   "\nDexterity:\t\t" + myChar.dexterity() +
						   "\nLuck\t\t\t" + myChar.luck() +
						   "\nExp pts:\t\t" + myChar.exp() +
						   "\n--------------------------");	
		printInv(myChar);
	}
	
	/**
	 * displays character inventory -- not fully implemented
	 * @param myChar object.inventory() to display
	 */
	public static void printInv(Character myChar){
		
	
		if (myChar.inventory().getCount() == -1){
			System.out.println("No Items in inventory");
		}
	}
	

} // end of main
