import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Items fills an ArrayList with item data from the items.txt file
 * @author dustinrosebery
 * 
 * To add items to the list edit items.txt file through gitHub and use the format
 * 
 * Item_description value(int) weight(double)
 * ex; remote_controller 10 0.5
 */
public class Items implements Serializable{
	
	private static final long serialVersionUID = 1l;
	static int debugPopulate = 0; // set to 1 for input list print
	
	public static ArrayList<Items> itemList = new ArrayList<Items>();
	
	private String itemName;
	private double weight;
	private int value;
	
	public String getName(){
		return itemName;
	}
	public double getWeight(){
		return weight;
	}
	public int getValue(){
		return value;
	}
	
	Items(String name, int value, double weight){
		itemName = name;
		this.weight = weight;
		this.value = value;
	}
	
	/**
	 * Populates the list using items.txt
	 * Item index corresponds to the line number of items.txt - 1
	 */
	static void populate(){
		
		try{
			 Scanner scan = new Scanner(new File("items.txt"));
			 scan.useDelimiter(" ");
			 String name;
			 double itemWeight;
			 int itemValue;
			 int index = 0;
			 
			 while (scan.hasNext()){
				 name = scan.next();
				 itemValue = Integer.parseInt(scan.next());
				 itemWeight = Double.parseDouble(scan.nextLine());
				 
				 Items insert = new Items(name, itemValue, itemWeight);
				 itemList.add(index, insert);
				 
				 if (debugPopulate == 1){
					 insert = itemList.get(index);
					 System.out.println("Item " + index + ": " + insert.getName()
							 			+ "\nweight: " + insert.getWeight() + "\n"
							 			+ "value: $" + insert.getValue() + "\n");
				 }
				 
				 index++;
			 }
			 scan.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
