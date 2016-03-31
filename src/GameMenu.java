import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is where you land after character creation/loading. It is also for testing regex input capture. -- java.util.regex --
 * @author dustinrosebery
 *
 */
abstract class GameMenu {

	/**
	 * Set enableTesting to true, and modify the REGEX_TEST string (in quotations) with the regex command
	 * the console will wait for input and you can type something in. hit enter to end input
	 * returns "True" if input was matched
	 * returns "False" if no match found
	 * type quit to exit to main menu
	 * 
	 * testing set to false will run GameMenu normally
	 */
	private static boolean enableTesting = true; 		
	private static final String REGEX_TEST = "open drawer|look in drawers|look in drawer|search drawers"; 
	
	
	/**
	 * These patterns 
	 */
	private static String playPattern;
	
	
	public static void start(){
		
		if (enableTesting){
			regexTesting();
		}
		else
		{
			System.out.println("\nWell whats your plan?" +
					 "\n**************************" +
					 "\n" +
					 "\n--------------------------" +
					 "\nlets roll" +
					 "\n--------------------------" +
					 "\n" +
					 "\n--------------------------" +
					 "\nhuh?" +
					 "\n--------------------------" + 
					 "\n" + 
					 "\n--------------------------" +
					 "\nWalk Away?" +
					 "\n--------------------------");
			
			String startCatch = "lets roll|Let's Roll|start|play|lets go";			// Strings you think users might input for the menu options
			String tutCatch = "huh?|huh|Huh?|Huh|what?|What?|what|What";
			String quitCatch = "walk away|Walk Away|Walk away|exit|main menu|quit";
			
			Pattern startPatt = Pattern.compile(startCatch);						// Regex pattern objects
			Pattern tutPatt = Pattern.compile(tutCatch);
			Pattern quitPatt = Pattern.compile(quitCatch);
			
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			
			Matcher startMatch = startPatt.matcher(input);							// Regex Matcher objects
			Matcher tutMatch = tutPatt.matcher(input);
			Matcher quitMatch = quitPatt.matcher(input);
			
			if (startMatch.find()){
				System.out.println("Starting Game!!");
				System.out.println("Just Kidding, game not implemented");
				Main.mainMenu();
			}
			else if (tutMatch.find()){
				System.out.println("Starting Tutorial!!");
				System.out.println("Steal stuff");
				Main.mainMenu();
			}
			else if (quitMatch.find()){
				System.out.println("Exiting to main menu");
				Main.mainMenu();
			}
			else{
				System.out.println("What was that?");
				start();
			}
		} // end enableTesting else statement
	} // end start
	
	
	/**
	 * Method for testing regex commands. loops until "quit" is input
	 */
	public static void regexTesting(){
		
		boolean found = false;
		
		System.out.println( "\nRegex Testing" +
							"\n**************************");
		
		Scanner scan = new Scanner(System.in);
		Pattern testPatt = Pattern.compile(REGEX_TEST);	// pattern object, REGEX_TEST defined at top of class
			
		String line = scan.nextLine();
		
		if (line.compareTo("quit") == 0){	// end test
			Main.printMenu();
		}
		
		Matcher testMatch = testPatt.matcher(line);		// matcher object 
		
		if (testMatch.find()){
			System.out.println("True");
		}
		else{
			System.out.println("False");
		}
		
		start();
	} // end regexTesting
	
	
	
} // end GameMenu
