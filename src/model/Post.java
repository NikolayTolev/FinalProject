package model;

public class Post {

	private int id;
	private int userId;
	private int sectionId;
	private String imageURL;
	private String title;
	
	public Post(int userId, int sectionId, String title) {
		super();
		this.userId = userId;
		this.sectionId = sectionId;
		this.title = title;
	}

	public Post(int id, int userId, int sectionId, String imageURL, String title) {
		this(userId, sectionId, title);
		this.id = id;
		this.imageURL = imageURL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getUserId() {
		return userId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public String getTitle() {
		return title;
	}
}
