import java.util.Scanner;

/**
 * Main guides the user through the game menus
 * @author dustinrosebery
 *
 */
public class Main {

	public static void main(String[] args){
		
		System.out.println("\nWelcome to TheNightShift" +
						   "\n--------------------------");
		Items.populate();
		Saves.readScores();
		mainMenu();
		
		
	}
	
	public static void printMenu(){
		System.out.println("\n\n\tMain Menu" +
						   "\n**************************" +
						   "\n1. Create a new character" +
						   "\n2. Load a saved Character" +
						   "\n3. View leaderboard" +
						   "\n4. Show help menu" +
						   "\n5. quit");
	}
	
	public static void mainMenu(){ 
		printMenu();
		String choice = "";
		String name;
		
		System.out.println("\n\nWhat would you like to do?\t");
		Scanner scan = new Scanner(System.in);
		Character myChar;
		
		choice = scan.next();
		
		switch (choice) {
		
		case ("1") : 	// create a new character		
			System.out.println("Enter your characters name\t");
			name = scan.next();
			
			if (name.compareTo("") == 0){
				System.out.println("invalid name.");
				mainMenu();
			}
			else{
				myChar = new Character(name);
				Saves.writeChar(myChar);
				LeaderBoard.addScore(myChar.score());
				System.out.println( "\n**************************"
									+ "\n" + name + " created and saved!" 
									+ "\n**************************");
				printCharacter(myChar);
				GameMenu.start();
			}
			
			
			
		case ("2") :	// load a character
			System.out.println("Which character would you like to play?");
			name = scan.next();
			myChar = Saves.readChar(name);
			
			if (myChar.getName() == ""){
				System.out.println(name + " was not found");
				mainMenu();
			}
			else{
				System.out.println( "\n**************************"
									+ "\n" + name + " loaded successfully!" 
									+ "\n**************************");
				printCharacter(myChar);
				GameMenu.start();
			}
			
			
		case ("3") :	// View leaderboard
			System.out.println("\nLeaderBoard not yet implemented\n");
		
		case ("5") :	// quit program
			break;
			
		default :	// check for valid input
			System.out.println("please enter the number of your choice.");
			mainMenu();
			
		}// end switch	
		scan.close();
	}// end mainMenu
	
	public static void printCharacter(Character myChar){
		
		System.out.println("\nCharacter Information." +
							"\n**************************");
		System.out.println("Name: " + myChar.getName() +
						 "\n--------------------------" +
						   "\nStrength:\t\t" + myChar.getStr() +
						   "\nReflex:\t\t\t" + myChar.getRef() +
						   "\nIntelligence:\t\t" + myChar.getInt() +
						   "\nPerception:\t\t" + myChar.getPerc() +
						   "\nDexterity:\t\t" + myChar.getDex() +
						   "\nLuck\t\t\t" + myChar.getLuck() +
						   "\nExp pts:\t\t" + myChar.getExp() +
						   "\n--------------------------");	
		printInv(myChar);
	}
	
	public static void printInv(Character myChar){
		
	
		if (myChar.inventory().getCount() == -1){
			System.out.println("No Items in inventory");
		}
	}
	

} // end of main
