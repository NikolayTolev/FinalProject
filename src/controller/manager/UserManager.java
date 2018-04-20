package controller.manager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;
import model.dao.UserDAO;
import model.db.DBManager;
import util.exceptions.RegisterException;

public class UserManager {

	private volatile static UserManager instance = null;
	private static Connection con;
	
	private UserManager() {
		con = DBManager.getInstance().getConnection();
	}
	
	public static UserManager getInstance() {
		if(instance == null) {
			synchronized (UserManager.class) {
				if(instance == null) {
					instance = new UserManager();
				}
			}
		}
		return instance;
	}
	
	public void registerUser(User u) throws RegisterException, SQLException {
		if (u.getUsername() == null || u.getUsername().length()<5) {
			throw new RegisterException("Username must be at least 5 chars long");
		}
		if (UserDAO.getInstance().checkUsername(u.getUsername())) {
			throw new RegisterException("Username already exists");
		}
		if (u.getPassword() == null || u.getPassword().length()<5) {
			throw new RegisterException("password should be at least 5 chars long");
		}
		if (u.getFirstName() == null) {
			throw new RegisterException("enter first name");
		}
		if (u.getLastName() == null) {
			throw new RegisterException("enter last name");
		}
		if (!emailValidator(u.getEmail())) {
			throw new RegisterException("inalid email");
		}
		if (UserDAO.getInstance().checkForEmail(u.getEmail())) {
			throw new RegisterException("email already exists");
		}
		
		UserDAO.getInstance().saveUser(u);
	}
	
	public void loginUser(String username, String password) throws Exception {
		if(username == null || password == null || !UserDAO.getInstance().checkUser(username, password)) {
			throw new Exception("Invalid username/password");
		}
	}
	
	private boolean emailValidator(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
	}
}
