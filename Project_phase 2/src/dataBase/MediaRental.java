package dataBase;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRental implements MediaRentalInt{
	private ArrayList <Customer> customers = new ArrayList<>();
	private ArrayList <Media> media = new ArrayList<>();
	private Media searchPointer;//this variable will be pointing to the media when using searchMedia
	public ArrayList <String> tmpRentedList = new ArrayList<>();//it is set to public so that the Main has access to these list so when we read from a file we add the rented media titles to them
	public ArrayList <String> tmpCartList = new ArrayList<>();
//	a custom Exception (ItemNotFound) was used in some methods
	
	public MediaRental() {}

	public void addCustomer(String id, String name, String address, String phone, String plan) {
		customers.add(new Customer(id, name, address, phone, plan, tmpCartList, tmpRentedList));//media in tmpRentedList will be added from main driver
		tmpCartList.clear();//we clear the lists so that we can use it for another customer
		tmpRentedList.clear();
	}

	public void addMovie(String code, String title, int copiesAvailable, String rating) {
		rating = rating.toUpperCase();
		if(rating.equals("AC") || rating.equals("HR") || rating.equals("DR"))
			media.add(new Movie(code, title, copiesAvailable, rating));
	}

	public void addGame(String code, String title, int copiesAvailable, double wieght) {
		media.add(new Game(code, title, copiesAvailable, wieght));
	}

	public void addAlbum(String code, String title, int copiesAvailable, String artist, String songs) {
		media.add(new Album(code, title, copiesAvailable, artist, songs));
	}

	public void setLimitedPlanLimit(int value) {
		Customer.setMediaLimit(value);
	}

	public String getAllCustomersInfo() { 
		Collections.sort(customers);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < customers.size(); i++)
			sb.append(customers.get(i) + "\n"); //this will automatically invoke the toString method
		
		return sb.toString();
	}

	public String getAllMediaInfo() {
		Collections.sort(media);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < media.size(); i++)
			sb.append(media.get(i) + "\n");
		
		return sb.toString();
	}

	public boolean addToCart(String customerId, String mediaCode) throws ItemNotFoundException {
		Customer tmpCustomer = searchCustomer(customerId);
		Media tmpMedia = searchMediaCode(mediaCode);
		if(!tmpCustomer.getInterested().contains(tmpMedia.getTitle())) {
				tmpCustomer.getInterested().add(tmpMedia.getTitle());
				return true;
		}
		return false;
	}

	public boolean removeFromCart(String customerId, String mediaCode) {
		try {
			Customer tmpCustomer = searchCustomer(customerId);
			Media tmpMedia = searchMediaCode(mediaCode);
			if(tmpCustomer.getInterested().contains(tmpMedia.getTitle())) {
				tmpCustomer.getInterested().remove(tmpMedia.getTitle());
				return true;
			}
		}
		catch(ItemNotFoundException ex) {
			System.out.println(ex);
		}
		return false;
	}
	
	public String processRequests(){
		StringBuilder sb = new StringBuilder();
		ArrayList <String> tmpList = new ArrayList<>();
		Collections.sort(customers);
		
		for(int i = 0; i < customers.size(); i++) {
			tmpList = customers.get(i).getInterested();
			/*
			 * if the items rented by the customer who has limited plan and the number of items in his cart will exceed the customer limit then it will move on to the next customer
			 * without clearing his cart and let the customer choose what items he wishes to discard 
			 */
			if(customers.get(i).getPlan().equals("LIMITED") && customers.get(i).getRented().size() + tmpList.size() > Customer.getMediaLimit()) {
				sb.append("Remove some Items from \"" + customers.get(i).getName() + "\" cart and try again");
				continue;
			}
			else {
				for(int j = 0; j < tmpList.size(); j++) {
					searchMedia(null, tmpList.get(j));//after using searchMedia the search pointer will be pointing to the media we are looking for
					
					//this if statment will check if the items has enough copies, and if the item is already rented then it will not add a duplicate 
					if(searchPointer.getCopiesAvailable() != 0 && !customers.get(i).getRented().contains(tmpList.get(j))) {
							sb.append("Sending [" + tmpList.get(j) + "] to [" + customers.get(i).getName() + "]\n");
							searchPointer.setCopiesAvailable(searchPointer.getCopiesAvailable() - 1);//since searchPointer is pointing to media in the media list then the media in the list will be affected
					}
				}
				customers.get(i).setRented(tmpList);
			}
			tmpList.clear(); //this will clear both the searchPointersearchPointer and the interested media array list because they both point to the same list
		}
		return sb.toString();
	}

	public boolean returnMedia(String customerId, String mediaCode) {
		try {
			Customer tmpCustomer = searchCustomer(customerId);
			Media tmpMedia = searchMediaCode(mediaCode);
			if(tmpCustomer.getRented().contains(tmpMedia.getTitle())) {
				tmpCustomer.getRented().remove(tmpMedia.getTitle());
				tmpMedia.setCopiesAvailable(tmpMedia.getCopiesAvailable() + 1);//since searchPointer is pointing to media in the list then the media in the list will be affected
				return true;
			}
		}
		catch(ItemNotFoundException ex) {
			System.out.println(ex);
		}
		return false;
	}

	public ArrayList<String> searchMedia(String code, String title){
		ArrayList<String> list = new ArrayList<>();
		
		if(code != null) {
			try {
				searchPointer = searchMediaCode(code);
				list.add(searchPointer.toString());
			}
			catch(ItemNotFoundException ex) {
				list.add(code + " Was not found");
			}
		}
		else if(title != null) {
			for(Media m : media) {
				if(m.getTitle().equalsIgnoreCase(title)) {
					list.add(m.getTitle());
					searchPointer = m;
					break;
				}
			}
		}
		else
			for(Media m : media)
				list.add(m.getTitle());
		
		return list;
	}
	
	public void deleteCustomer(String id) throws ItemNotFoundException {
		customers.remove(searchCustomer(id));
	}
	
	public void deleteMedia(String code) throws ItemNotFoundException {
		media.remove(searchMediaCode(code));
	}
	/*
	 *the following two methods were used to search for certain customer by name OR certain media by title and if nothing is found then
	 *it throws an ItemNotFoundException which is a custom exception
	 */
	public Customer searchCustomer(String id) throws ItemNotFoundException {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getId().equalsIgnoreCase(id))
				return customers.get(i);
		}
		throw new ItemNotFoundException(id);
	}
	
	public Media searchMediaCode(String code) throws ItemNotFoundException {
		for(Media m : media)
			if(m.getCode().equals(code))
				return m;
		
		throw new ItemNotFoundException(code);
	}
}//end class
