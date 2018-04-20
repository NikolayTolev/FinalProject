package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import model.db.DBManager;

public class UserDAO {

	private volatile static UserDAO instance = null;
	private static Connection con;
	
	private UserDAO() {
		con = DBManager.getInstance().getConnection();
	}
	
	public static UserDAO getInstance() {
		if(instance == null) {
			synchronized (UserDAO.class) {
				if (instance == null) {
					instance = new UserDAO();
				}
			}
		}
		return instance;
	}
	
	public void saveUser(User u) throws SQLException {
		String sql = "INSERT INTO users (email, password, username, first_name, last_name, country) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getUsername());
		ps.setString(4, u.getFirstName());
		ps.setString(5, u.getLastName());
		ps.setString(6, u.getCountry());
		ps.executeUpdate();
	}
	
	public User getUser(String username, String password) throws SQLException {
		String sql = 	"SELECT id, email, password, username, first_name, last_name, phot, biography, country FROM users"+
						" WHERE username=? AND password=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			return new User(rs.getInt("id"), 
							rs.getString("first_name"), 
							rs.getString("last_name"), 
							rs.getString("username"), 
							rs.getString("password"), 
							rs.getString("email"), 
							rs.getString("country"));
		} else {
			return null;
		}
	}
}
