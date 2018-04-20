package controller.manager;
import java.sql.Connection;
import model.db.DBManager;

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
}
