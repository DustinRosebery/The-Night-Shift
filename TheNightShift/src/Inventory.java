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
	}
	
	/**
	 * returns an item in the players inventory
	 * @param index
	 * @return
	 */
	public Items getItem(int index){
		
		return myList.get(index);
	}
	
	public void delete(Items item){
		myList.remove(item);
	}
	
	public int getCount(){
		return count;
	}
	

}
