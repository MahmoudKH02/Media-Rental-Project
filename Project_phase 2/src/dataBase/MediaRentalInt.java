package dataBase;

import java.util.ArrayList;

public interface MediaRentalInt {
	public void addCustomer(String id, String name, String address, String phone, String plan);
	public void addMovie(String code, String title, int copiesAvailable, String rating);
	public void addGame(String code, String title, int copiesAvailable, double wieght);
	public void addAlbum(String code, String title, int copiesAvailable, String artist, String songs);
	public void setLimitedPlanLimit(int value);
	public String getAllCustomersInfo();
	public String getAllMediaInfo();
	public boolean addToCart(String customerId, String mediaCode) throws ItemNotFoundException;
	public boolean removeFromCart(String customerId, String mediaCode);
	public String processRequests();
	public boolean returnMedia(String customerID, String mediaCode);
	public ArrayList<String> searchMedia(String code, String title);
}
