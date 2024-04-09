package dataBase;

public class ItemNotFoundException extends Exception {
	//this exception is for the items that are not found in an array or array list
	public ItemNotFoundException(String title) {
		super(title + " Does Not Exist");
	}
}
