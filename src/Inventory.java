import java.io.Serializable;
import java.util.ArrayList;

/**
 * Inventory class to separate and hold "items" for each character,
 * as well as recording value and weight
 * @author dustinrosebery
 *
 */
public class Inventory implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	private ArrayList<Items> myList;
	
	private double totalWeight;
	private double totalValue;
	private int count;	
	
	Inventory(){
		myList = new ArrayList<Items>();
		totalWeight = 0;
		totalValue = 0;	
		count = -1; 
	}
	
	/**
	 * Adds an item to the players inventory 
	 * @param item - Items object from itemList
	 */
	public void add(Items item){
		count++;
		myList.add(count, item);
		calcValue();
		calcWeight();
	}

	/**
	 * @param itemName Name of item to check
	 * @return If inventory contains item
     */
	public boolean contains(String itemName) {
		boolean contains = false;
		for (Items item : myList)
			if (item.getName().equalsIgnoreCase(itemName))
				contains = true;
		return contains;
	}

	/**
	 * Gets inventory item at index
	 * @param index
	 * @return item
	 */
	public Items getItem(int index){
		
		return myList.get(index);
	}

	/**
	 * @return inventory
     */
	public ArrayList<Items> getList() {
		return myList;
	}

	/**
	 * @return total weight of inventory
     */
	public double getWeight() {
		return totalWeight;
	}

	/**
	 * @return total value of inventory
     */
	public double getValue() {
		return totalValue;
	}
	
	/**
	 * removes an item from player inventory
	 * @param item
	 */
	public void delete(Items item){
		myList.remove(item);
	}
	
	/**
	 * returns current inventory item count
	 * @return count
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * Calculates total value of all items currently in the players inventory
	 */
	public void calcValue(){
	
		if (!myList.isEmpty()){
			totalValue = 0;
			for (Items item : myList) {
				totalValue += item.getValue();
			}
		}
	}
	
	/**
	 * Calculates total weight of items in current inventory
	 */
	public void calcWeight(){
		
		if (!myList.isEmpty()){
			totalWeight = 0;
			for (Items item : myList) {
				totalWeight += item.getWeight();
			}
		}
	}

}
