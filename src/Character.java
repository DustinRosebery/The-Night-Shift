import java.io.Serializable;
import java.util.ArrayList;

/**
 * Characters holds all relevent data in one serializable package
 * @authors dustinrosebery, Connor Nelson
 */
public class Character implements Serializable {

    private static final long serialVersionUID = 1l; // required for serialization

    private boolean escaping;
    private boolean escaped;
    private boolean caught;

    private String name;
    private Inventory inventory;                                // each character has their own inventory
    private LeaderBoard leaderboard;                            // and LeaderBoard objects
    private ArrayList<Rooms> map;                               // and room map

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
        escaping = false;
        escaped = false;
        caught = false;

        this.name = name;
        inventory = new Inventory();
        leaderboard = new LeaderBoard();

        map = new ArrayList<Rooms>();
        roomIndex = 0;

        this.strength = strength;
        this.reflex = reflex;
        this.intelligence = intelligence;
        this.perception = perception;
        this.dexterity = dexterity;
        this.luck = luck;
        this.exp = exp;
    }

    public void addItem(Items item) {

        if (inventory.getWeight() + item.getWeight() <= strength * 10) {
            inventory.add(item);

            int value = item.getValue();
            int exp = value / 20;

            if (exp < 1)
                exp = 1;
            else if (exp > 3)
                exp =3;

            this.exp += exp;

            Game.getController().updateCharacter(this);
        }
        else
            Game.getController().write("You must increase your strength to carry that much weight");
    }

    /**
     * Default character with name
     */
    public Character(String name) {

        this(name, 10, 10, 10, 10, 10, 10, 0);
    }

    /**
     * Default character
     */
    public Character() {
        this("Anonymous");
    }

    /**
     * @param index of room to retrieve
     * @return the characters room map
     */
    public String getRoomName(int index) {
        return map.get(index).getName();
    }

    /**
     * @param index of desired room
     * @return string description of room
     */
    public String getRoomDesc(int index) {
        return map.get(index).getDesc();
    }

    /**
     * instantiates the room map for new players
     */
    public void initMap() {
        map.add(0, new Outside());
        map.add(1, new Basement());
        map.add(2, new LivingRoom());
        map.add(3, new Bedroom());
        map.add(4, new Kitchen());
        map.add(5, new Garage());
    }

    /**
     * @param index Index of item in inventory
     * @return Item in inventory at index
     */
    public Items getItem(int index) {
        return inventory.getItem(index);
    }

    /**
     * @return character room at current index
     */
    public Rooms currentRoom() {
        return map.get(this.index());
    }

    /**
     * performs a hidden roll against a specified stat
     * @param stat to check
     * @return true on success
     */
    public boolean skillCheck (String stat) {
        boolean conclusion = false;
        Game.getController().write("");

        int roll = Game.getController().rollDice();
        if (stat.compareTo("strength") == 0) {
            if (roll <= strength)
                conclusion = true;
        }
        else if (stat.compareTo("intelligence") == 0) {
            if (roll <= intelligence)
                conclusion = true;
        }
        else if (stat.compareTo("reflex") == 0) {
            if (roll <= reflex)
                conclusion = true;
        }
        else if (stat.compareTo("perception") == 0) {
            if (roll <= perception)
                conclusion = true;
        }
        else if (stat.compareTo("dexterity") == 0) {
            if (roll <= dexterity)
                conclusion = true;
        }
        else if (stat.compareTo("luck") == 0) {
            if (roll <= luck)
                conclusion = true;
        }

        return conclusion;
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
     * @param index sets current Room
     */
    public void setIndex (int index) { roomIndex = index; }

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

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

    public boolean isEscaping() {
        return escaping;
    }

    public void setEscaping(boolean escaping) {
        this.escaping = escaping;
    }

    public boolean isEscaped() {
        return escaped;
    }

    public void setEscaped(boolean escaped) {
        this.escaped = escaped;
    }
}
