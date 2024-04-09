package dataBase;

import java.util.ArrayList;

public class Customer implements Comparable<Customer>{
	private String id;
	private String name;
	private String address;
	private String phone;
	private String plan;
	private ArrayList <String> interested = new ArrayList<>();
	private ArrayList <String> rented = new ArrayList<>();
	private static int MediaLimit = 2; //this is static because it is general to all customers who have limited plan
	
	public Customer() {}
	
	public Customer(String id, String name, String address, String phone, String plan, ArrayList <String> interested, ArrayList <String> rented) {
		this.id = id;
		this.name = name.toUpperCase();
		this.address = address;
		this.phone = phone;
		this.plan = plan.toUpperCase();
		for(int i = 0; i < interested.size(); i++)
			this.interested.add(interested.get(i));
		
		for(int i = 0; i < rented.size(); i++)
			this.rented.add(rented.get(i));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan.toUpperCase();
	}

	public ArrayList<String> getInterested() {
		return interested;
	}

	public void setInterested(ArrayList<String> interested) {
		for(int i = 0; i < rented.size(); i++)
			this.rented.add(rented.get(i));
	}

	public ArrayList<String> getRented() {
		return rented;
	}

	public void setRented(ArrayList<String> rented) {
		for(int i = 0; i < rented.size(); i++)
			this.rented.add(rented.get(i));
	}

	public static int getMediaLimit() {
		return MediaLimit;
	}

	public static void setMediaLimit(int mediaLimit) {
		Customer.MediaLimit = mediaLimit;
	}

	public String toString() {
		return "Customer-" + id + "-" + name + "-" + address + "-" + phone + "-" + plan + "-Cart" + getInterested() + "-Rented" + getRented();
	}

	public boolean equals(Object obj) {	//compares two customers based on their name
		boolean eq = false;
		if(obj instanceof Customer)
			eq = this.getName().equals(((Customer)obj).getName());
		return eq;
	}
	
	public int compareTo(Customer customer) {
		return this.name.compareTo(customer.getName());
	}
}
