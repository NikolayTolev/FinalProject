package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Comment;
import model.Post;
import model.db.DBManager;

public enum CommentDAO implements ICommentDAO {

	COMMENT_DAO;
	private Connection con;
	
	private CommentDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public Comment getCommentById(int id) throws Exception {
		String sql="SELECT id, first_name, last_name, username, password, email FROM users WHERE id =? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		return null;
	}

	@Override
	public void saveComment(Post p, Comment c) throws Exception {
		String sql = "INSERT INTO comments (content, user_id, post_id) VALUES (?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getContent());
		ps.setInt(2, c.getOwner().getId());
		ps.setInt(3, p.getId());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		c.setId(rs.getInt("id"));
	}

	@Override
	public void deleteComment(Comment c) throws Exception {
		String sql = "DELETE FROM comments WHERE id=? OR parent_id=?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, c.getId());
		ps.setInt(2, c.getId());
		ps.executeUpdate();
	}

	@Override
	public void saveSubComment(Comment parent, Comment child) throws SQLException {
		String sql = "INSERT INTO comments (content, user_id, post_id, parent_id) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, child.getContent());
		ps.setInt(2, child.getOwner().getId());
		ps.setInt(3, parent.getPost().getId());
		ps.setInt(4, parent.getId());
		ps.executeUpdate();
	}
}
