package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.BCrypt;
import model.User;
import model.db.DBManager;

public enum UserDAO implements IUserDAO {

	USER_DAO;
	private Connection con;

	private UserDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public void saveUser(User u) throws SQLException {
		String sql = "INSERT INTO users (email, password, username, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, u.getEmail());
		ps.setString(2, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
		ps.setString(3, u.getUsername());
		ps.setString(4, u.getFirstName());
		ps.setString(5, u.getLastName());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		u.setId(rs.getInt("id"));
	}

	@Override
	public void deleteUser(User u) throws SQLException {
		String sql = "DELETE FROM users WHERE username=? AND email=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getEmail());
		ps.executeUpdate();
	}

	@Override
	public User getUserById(int id) throws SQLException {
		String sql = "SELECT id, email, password, username, first_name, last_name, photo, biography, country FROM users"
				+ " WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), 
							rs.getString("username"), rs.getString("password"), rs.getString("email"));
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) throws SQLException {
		String sql = "SELECT id, email, password, username, first_name, last_name, photo, biography, country FROM users"
				+ " WHERE username=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), 
							rs.getString("username"), rs.getString("password"), rs.getString("email"));
		}
		return null;
	}

	@Override
	public String checkForEmail(String email) throws SQLException {
		String sql = "SELECT email FROM users WHERE email=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString("email");
		}
		return null;
	}

	@Override
	public String checkForUsername(String username) throws SQLException {
		String sql = "SELECT username FROM users WHERE username=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString("username");
		}
		return null;
	}
}
