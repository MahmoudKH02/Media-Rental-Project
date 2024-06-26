package dataBase;

public class Album extends Media{
	private String artist;
	private String songs;
	
	public Album() {}
	
	public Album(String code, String title, int copiesAvailable, String artist, String songs) {
		super(code, title, copiesAvailable);
		this.artist = artist;
		this.songs = songs;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}
	
	public String toString() {
		return "Album-" + code + "-" + title + "-" + copiesAvailable + "-" + artist + "-" + songs;
	}
	
}
