package model.dao;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Tag;
import model.db.DBManager;

public enum TagDAO implements ITagDAO {

	TAG_DAO;
	private Connection con;
	
	private TagDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public void saveTag(Tag g) throws SQLException {
		String sql = "INSERT INTO tags (name) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, g.getName());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		g.setId(rs.getInt("id"));
	}

	@Override
	public Tag getTagById(int id) throws SQLException {
		String sql = "SELECT id, name FROM tags WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Tag tag = null;
		while(rs.next()) {
			tag = new Tag(rs.getInt("id"), rs.getString("name"));
		}
		return tag;
	}
}
