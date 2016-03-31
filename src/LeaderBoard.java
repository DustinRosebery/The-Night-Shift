import java.io.Serializable;
import java.util.ArrayList;

/**
 * LeaderBoard contains all recorded scores in the savedScores arrayList
 * Each character gets one and that will be used to save player data over multiple characters
 * @author dustinrosebery
 *
 */
public class LeaderBoard implements Serializable {
	
	private static final long serialVersionUID = 1l;
	private static ArrayList<LeaderBoard> savedScores = new ArrayList<LeaderBoard>(); 
	
	private String charName;
	private int currentValue;
	private int totalValue;
	private double currentTime;
	private double totalTime;
	private int gamesCompleted;
	
	/**
	 * default constructor
	 */
	LeaderBoard(){}
	
	/**
	 * Constructor for creating a LeaderBoard object for new characters
	 * @param name of new character
	 */
	LeaderBoard(String name){
		
		charName = name;
		currentValue = 0;
		totalValue = 0;
		gamesCompleted = 0;
		currentTime = 0.00;
		totalTime = 0.00;
	}
	
	/**
	 * loads the savedScores list from leaderboard.ser and checks for not found or empty list
	 */
	public static void loadHiScores(){
		
		ArrayList<LeaderBoard> nullCheck = Saves.readScores();
		
		if (nullCheck.isEmpty() == true){
			System.out.println("Error: LeaderBoard empty or not found");
		}
		else{
			savedScores = nullCheck;
		}
	}
	
	/**
	 * If the array doesn't already contain the characters object it is put in the savedScores list
	 * and the list is written to leaderboard.ser
	 * @param charScore to insert
	 */
	public static void addScore(LeaderBoard charScore){
		
		if (savedScores.contains(charScore) == true){
			return;
		}
		else{
			savedScores.add(charScore);
			Saves.writeScores(savedScores);
		}
	}
	
	
	
	/**
	 * getter method
	 * @return name
	 */
	public String getName(){
		return charName;
	}
	/**
	 * getter method
	 * @return currentValue
	 */
	public int getCurrentValue(){
		return currentValue;
	}
	/**
	 * getter method
	 * @return totalValue
	 */
	public int getTotalValue(){
		return totalValue;
	}
	/**
	 * getter method
	 * @return currentTime
	 */
	public double getCurrentTime(){
		return currentTime;
	}
	/**
	 * getter method
	 * @return totalTime
	 */
	public double getTotalTime(){
		return totalTime;
	}
	
	
	/**
	 * setter method
	 * @param value 
	 */
	public void setCurrentValue(int value){
		currentValue = value;
	}
	/**
	 * setter method that adds to the total value statistic
	 * @param value
	 */
	public void addTotalValue(int value){
		totalTime = totalTime + value;
	}
	/**
	 * setter method
	 * @param time
	 */
	public void setCurrentTime(double time){
		currentTime = time;
	}
	/**
	 * setter method that adds to the total time statistic
	 * @param time
	 */
	public void addTotalTime(double time){
		totalTime = totalTime + time;
	}
	/**
	 * adds 1 to number of games completed
	 */
	public void addGame(){
		gamesCompleted++;
	}
} // end LeaderBoard
