package controller.manager;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.BCrypt;
import model.User;
import model.dao.UserDAO;
import util.exceptions.RegisterException;

public enum UserManager {

	USER_MANAGER;
	private static final String[] EMAIL_DOMAINS = {"abv.bg", "gmail.com", "yahoo.com", "hotmail.com", "aol.com", "msn.com"};

	public void registerUser(User u) throws RegisterException, SQLException {

		if (u.getUsername() == null || u.getUsername().length() < 5) {
			throw new RegisterException("Username must be at least 5 chars long");
		}
		if (UserDAO.USER_DAO.checkForUsername(u.getUsername()) != null &&
			UserDAO.USER_DAO.checkForUsername(u.getUsername()).length() > 0) {
			throw new RegisterException("Username already exists");
		}
		if (u.getPassword() == null || u.getPassword().length() < 5) {
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
		if (UserDAO.USER_DAO.checkForEmail(u.getEmail()) != null &&
			UserDAO.USER_DAO.checkForEmail(u.getEmail()).length() > 0) {
			throw new RegisterException("email already exists");
		}

		UserDAO.USER_DAO.saveUser(u);
	}

	public void loginUser(String username, String password) throws Exception {
		User u = UserDAO.USER_DAO.getUserByUsername(username);
		if (username == null || password == null || u==null || !(u.getUsername().equals(username))
				|| !(BCrypt.checkpw(password, u.getPassword()))) {
			throw new Exception("Invalid username/password");
		}
	}

	private boolean emailValidator(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		
		if (matcher.find()) {
			String emailDomain = email.substring(email.indexOf("@")+1);
			System.out.println(emailDomain);
			for (String domain : EMAIL_DOMAINS) {
				if (emailDomain.equals(domain)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}
}
