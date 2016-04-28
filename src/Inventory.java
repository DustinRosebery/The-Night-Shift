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
	 * Gets inventory item at index
	 * @param index
	 * @return item
	 */
	public Items getItem(int index){
		
		return myList.get(index);
	}

	public ArrayList<Items> getList() {
		return myList;
	}

	public double getWeight() {
		return totalWeight;
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
	
		if (myList.isEmpty() != true){
			
			int index = 0;
			totalValue = 0;
			while (myList.get(index) != null){
				totalValue = totalValue + myList.get(index).getValue();
				index++;
			}
		}
	}
	
	/**
	 * Calculates total weight of items in current inventory
	 */
	public void calcWeight(){
		
		if (myList.isEmpty() != true){
			
			int index = 0;
			totalWeight = 0;
			while (myList.get(index) != null){
				totalWeight = totalWeight + myList.get(index).getWeight();
				index++;
			}
		}
	}

}
