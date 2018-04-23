package controller.manager;

import model.Comment;
import model.Post;
import model.dao.CommentDAO;

public enum CommentManager {
	
	COMMENT_MANAGER;
	
	public void writeComment(Post post, Comment c) throws Exception {
		if (post == null) {
			throw new Exception("No post given");
		}
		if (c == null || c.getContent().trim().isEmpty()) {
			throw new Exception("Comment should not be empty");
		}
		CommentDAO.COMMENT_DAO.saveComment(post, c);
	}
	
	public void removeComment(Comment c) throws Exception {
		if (c == null) {
			throw new Exception("Invalid comment id");
		}
		CommentDAO.COMMENT_DAO.deleteComment(c);
	}
	
	public void chandeComment(Comment c) {
		//TODO
	}
}
