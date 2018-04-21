package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {

	private int id;
	private User owner;
	private String content;
	private LocalDateTime time;
	private List<Comment> replies;
	
	public Comment(User user, String content, LocalDateTime time) {
		this.owner=user;
		this.content=content;
		this.time=time;
		this.replies=new ArrayList<>();
	}

	public Comment(int id, User user, String content, LocalDateTime time) {
		this(user, content, time);
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
}
