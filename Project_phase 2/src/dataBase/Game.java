package dataBase;

public class Game extends Media{
	private double weight;
	
	public Game() {}
	
	public Game(String code, String title, int copiesAvailable, double weight) {
		super(code, title, copiesAvailable);
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String toString() {
		return "Game-" + code + "-" + title + "-" + copiesAvailable + "-" + weight; 
	}
	
}
