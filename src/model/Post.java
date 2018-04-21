package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Post {

	private int id;
	private User owner;
	private Section section;
	private String imageURL;
	private String title;
	private List<Tag> tags;
	private List<Comment> comments;
	
	public Post(User u, String imageURL, String title, List<Tag> tags, Section section) {
		this.owner=u;
		this.imageURL = imageURL;
		this.title=title;
		this.tags=tags;
		this.section=section;
		this.comments=new ArrayList<>();
	}

	public Post(int id, User u, String imageURL, String title, List<Tag> tags, Section section) {
		this(u,imageURL,title,tags,section);
		this.id = id;
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

	public User getOwner() {
		return this.owner;
	}

	public Section getSection() {
		return section;
	}

	public String getTitle() {
		return title;
	}
	
	public List<Comment> getComments() {
		return Collections.unmodifiableList(comments);
	}
	
	public void deleteComment(Comment c) {
		this.comments.remove(c);
	}
}
