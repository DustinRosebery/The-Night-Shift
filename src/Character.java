import java.io.Serializable;

/**
 * Character holds the information for each character that plays the game
 *
 * @author dustinrosebery, Connor Nelson
 */
public class Character implements Serializable {

    private static final long serialVersionUID = 1l; // required for serialization

    private String name;
    private Inventory inventory;        // each character has their own inventory
    private LeaderBoard leaderboard;    // and LeaderBoard objects
    private Rooms map;                  // and room map
    private int roomIndex;

    private int strength;
    private int reflex;
    private int intelligence;
    private int perception;
    private int dexterity;
    private int luck;
    private int exp;

    /**
     * Creates a character based on given stats
     * @param name The name of the character
     * @param strength Strength
     * @param reflex Reflex
     * @param intelligence Intelligence
     * @param perception Perception
     * @param dexterity Dexterity
     * @param luck Luck
     * @param exp Experience points
     */
    public Character(String name, int strength, int reflex, int intelligence,
                     int perception, int dexterity, int luck, int exp) {
        this.name = name;
        inventory = new Inventory();
        leaderboard = new LeaderBoard();
        map = new Rooms();
        roomIndex = 0;

        this.strength = strength;
        this.reflex = reflex;
        this.intelligence = intelligence;
        this.perception = perception;
        this.dexterity = dexterity;
        this.luck = luck;
        this.exp = exp;
    }

    /**
     * Default character with name
     */
    public Character(String name) {

        this(name, 10, 10, 10, 10, 10, 10, 0);
        roomIndex = 0;
    }

    /**
     * Default character
     */
    public Character() {
        this("Anonymous");
        roomIndex = 0;
    }

    /**
     * @param index Index of item in inventory
     * @return Item in inventory at index
     */
    public Items getItem(int index) {
        return inventory.getItem(index);
    }


    /**
     * @return Name
     */
    public String name() {
        return name;
    }

    /**
     * @return Character's inventory
     */
    public Inventory inventory() {
        return inventory;
    }

    /**
     * @return Character's leaderboard
     */
    public LeaderBoard score() {
        return leaderboard;
    }


    /**
     * @return Strength
     */
    public int strength() {
        return strength;
    }

    /**
     * @return Reflex
     */
    public int reflex() {
        return reflex;
    }

    /**
     * @return Intelligence
     */
    public int intelligence() {
        return intelligence;
    }

    /**
     * @return Perception
     */
    public int perception() {
        return perception;
    }

    /**
     * @return Dexterity
     */
    public int dexterity() {
        return dexterity;
    }

    /**
     * @return Luck
     */
    public int luck() {
        return luck;
    }

    /**
     * @return Experience points
     */
    public int exp() {
        return exp;
    }

    /**
     * @return Index of current Room
     */
    public int index() { return roomIndex; }


    /**
     * Increases strength and decreases experience points
     * @param amount The amount to increase the strength by
     */
    public void addStrength(int amount) {
        strength += amount;
        exp -= amount;
    }

    /**
     * Increases reflex and decreases experience points
     * @param amount The amount to increase the reflex by
     */
    public void addReflex(int amount) {
        reflex += amount;
        exp -= amount;
    }

    /**
     * Increases intelligence and decreases experience points
     * @param amount The amount to increase the intelligence by
     */
    public void addIntelligence(int amount) {
        intelligence += amount;
        exp -= amount;
    }

    /**
     * Increases perception and decreases experience points
     * @param amount The amount to increase the perception by
     */
    public void addPerception(int amount) {
        perception += amount;
        exp -= amount;
    }

    /**
     * Increases dexterity and decreases experience points
     * @param amount The amount to increase the dexterity by
     */
    public void addDexterity(int amount) {
        dexterity++;
        exp -= amount;
    }

    /**
     * Increases luck and decreases experience points
     * @param amount The amount to increase the luck by
     */
    public void addLuck(int amount) {
        luck++;
        exp -= amount;
    }

    /**
     * Increases the amount of available experience points
     * @param amount The amount to increase the experience points by
     */
    public void addExp(int amount) {
        exp += amount;
    }

}
