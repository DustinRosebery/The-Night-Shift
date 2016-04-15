import java.util.Random;

/** This is a dice class with a single static method that will return the sum of
 * selected amount of dice to be rolled.
 * 
 * @author Trevor Rosenkilde
 * @version April 5, 2016
 */
public class Dice {
	
	private final static int NUM_OF_DICE = 3; //number of dice to be rolled
	static Random rng = new Random();
	
	/** This method will return the sum of all the dice rolled.
	 * 
	 * @return (int) sum of the all of the rolled dice
	 */
	public static int roll() { 
		int sumOfDiceRoll = 0; //initialize the variable
		for (int index = 0; index < NUM_OF_DICE; index++) { //will run for how many dice there are
			int diceRoll = (int)(Math.random() * 6 + 1); //utilizes Math.random() to get the random effect of a dice roll
			sumOfDiceRoll = sumOfDiceRoll + diceRoll; //add to the sum
			//System.out.println("Roll #" + index + ": " + diceRoll); //testing purposes to see what was rolled
		}
		return sumOfDiceRoll; //return the overall sum of the rolled dice
	}

	/**
	 * @param endVal
	 * @return a random number from n = 0 to n < endVal
     */
	public static int rand(int endVal) {
			return rng.nextInt(endVal);
	}
}
