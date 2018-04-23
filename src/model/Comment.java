package model;

import java.util.ArrayList;
import java.util.List;

public class Comment {

	private int id;
	private User owner;
	private Post post;
	private String content;
	private List<Comment> replies;
	
	public Comment(Post post, User user, String content) {
		this.owner=user;
		this.post = post;
		this.content=content;
		this.replies=new ArrayList<>();
	}

	public Comment(int id, Post post, User user, String content) {
		this(post, user, content);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return this.owner;
	}

	public String getContent() {
		return content;
	}
	
	public void addReply(Comment c) {
		this.replies.add(c);
	}
	
	public Post getPost() {
		return post;
	}
}
