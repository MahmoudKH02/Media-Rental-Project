package dataBase;

public abstract class Media implements Comparable<Media> {
	protected String code;
	protected String title;
	protected int copiesAvailable;
	
	public Media() {}
	
	public Media(String code, String title, int copiesAvailable) {
		this.code = code.toUpperCase();
		this.title = title.toUpperCase();
		this.copiesAvailable = copiesAvailable;
	}
	
	public abstract String toString();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.toUpperCase();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.toUpperCase();
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	
	public int compareTo(Media m) {
		return this.title.compareTo(m.title);
	}
	
}
