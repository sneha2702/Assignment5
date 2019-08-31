
public class Album {

	private String albumName;
	private String artistName;
	private String genre;

	public Album(String albumName, String artistName, String genre) {
		super();
		this.albumName = albumName;
		this.artistName = artistName;
		this.genre = genre;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "[albumName=" + albumName + ", artistName=" + artistName + ", genre=" + genre + "]";
	}
}
