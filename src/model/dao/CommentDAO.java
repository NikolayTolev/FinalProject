package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Comment;
import model.Post;
import model.db.DBManager;

public enum CommentDAO implements ICommentDAO {
	
    COMMENT_DAO;
	
	private Connection con;

	CommentDAO(){
		con=DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public Comment getCommentById(int id) throws SQLException {
		String sql="SELECT id, first_name, last_name, username, password, email FROM users WHERE id =? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet r = ps.executeQuery();
		
		return null;
	}

	@Override
	public void saveComment(Comment c) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(Post p) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSubComment(Comment parent, Comment child) {
		// TODO Auto-generated method stub
		
	}

}
