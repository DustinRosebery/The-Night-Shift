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
	private static final String REGEX_TEST = "Hello There"; 
	
	
	
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
			
		} // end enableTesting else statement
	} // end start
	
	
	/**
	 * Method for testing regex commands. loops until quit is input
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
