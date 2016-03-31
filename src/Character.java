import java.io.Serializable;

/**
 * Character holds the information for each character that plays the game
 * @author dustinrosebery
 *
 */
public class Character implements Serializable{
	
	private static final long serialVersionUID = 1l; // required for serialization
	
	private String name;
	private Inventory myInv; // each character has their own inventory 
	private LeaderBoard myScore; // and LeaderBoard objects
	
	private int strength;
	private int reflex;
	private int intelligence;
	private int perception;
	private int dexterity;
	private int luck;
	private int exp;
	
	/**
	 * Constructor called when creating a new character
	 * @param name chosen by the player
	 */
	Character(String name){
		
		this.name = name;
		myInv = new Inventory();
		myScore = new LeaderBoard(name);
		
		strength = 10;
		perception = 10;
		reflex = 10;
		intelligence = 10;
		dexterity = 10;
		luck = 10;
		exp = 0;
			
	}
	
	Character(){	// default constructor for null read results
		name = "";
	}	
	
	/**
	 * return characters inventory item at specified index
	 * @param index
	 * @return Item object at index
	 */
	public Items getItem(int index){
		return myInv.getItem(index);
	}
	
	
	/**
	 * getter method
	 * @return name
	 */
	public String getName(){
		return name;
	}
	/**
	 * returns characters Inventory object
	 * @return inventory
	 */
	public Inventory inventory(){
		return myInv;
	}
	/**
	 * returns characters LeaderBoard object 
	 * @return
	 */
	public LeaderBoard score(){
		return myScore;
	}
	
	
	/**
	 * getter method
	 * @return strength
	 */
	public int getStr(){
		return strength;
	}
	/**
	 * getter method
	 * @return reflex
	 */
	public int getRef(){
		return reflex;
	}
	/**
	 * getter method
	 * @return intelligence
	 */
	public int getInt(){
		return intelligence;
	}
	/**
	 * getter method
	 * @return perception
	 */
	public int getPerc(){
		return perception;
	}
	/**
	 * getter method
	 * @return dexterity
	 */
	public int getDex(){
		return dexterity;
	}
	/**
	 * getter method
	 * @return luck
	 */
	public int getLuck(){
		return luck;
	}
	/**
	 * getter method
	 * @return exp
	 */
	public int getExp(){
		return exp;
	}
	
	
	/**
	 * increases strength by 1
	 * @param increase
	 */
	public void addStr(){
		strength++;
	}
	/**
	 * increases reflex by 1
	 */
	public void addRef(){
		reflex++;
	}
	/**
	 * increases intelligence by 1
	 */
	public void addInt(){
		intelligence++;
	}
	/**
	 * increases perception by 1
	 */
	public void addPerc(){
		perception++;
	}
	/**
	 * increases dexterity by 1
	 */
	public void addDex(){
		dexterity++;
	}
	
} // end Character
