package model;

public class Comment {

	private int id;
	private int userId;
	private int postId;
	private int parentId;
	private String content;
	
	public Comment(int userId, int postId, int parentId, String content) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.parentId = parentId;
		this.content = content;
	}

	public Comment(int id, int userId, int postId, int parentId, String content) {
		this(userId, postId, parentId, content);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public int getPostId() {
		return postId;
	}

	public int getParentId() {
		return parentId;
	}

	public String getContent() {
		return content;
	}
}
