package model.dao;

public class UserDAO {

	private volatile static UserDAO instance = null;
	
	private UserDAO() {}
	
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
}
