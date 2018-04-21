package model.dao;

import java.sql.Connection;

import model.User;
import model.db.DBManager;

public enum UserDAO implements IUserDAO{
    
	USER_DAO;
	
	private Connection con;

	UserDAO(){
		con=DBManager.DB_MANAGER.getConnection();
	}
	
	@Override
	public void saveUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkForEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkForUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
