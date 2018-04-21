package model.dao;

import model.Comment;
import model.Post;

public interface ICommentDAO {
    
	Comment getCommentById(int id) throws Exception;
    void saveComment(Comment c) throws Exception;
    void deleteComment(Post p) throws Exception;
    void saveSubComment(Comment parent, Comment child);
}
