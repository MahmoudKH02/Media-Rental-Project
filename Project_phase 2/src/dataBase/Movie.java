package dataBase;

public class Movie extends Media {
	private String rating;

	public Movie() {}

	public Movie(String code, String title, int copiesAvailable, String rating) {
		super(code, title, copiesAvailable);
		this.rating = rating.toUpperCase();
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String toString() {
		return "Movie-" + code + "-" + title + "-" + copiesAvailable + "-" + rating;
	}

}
