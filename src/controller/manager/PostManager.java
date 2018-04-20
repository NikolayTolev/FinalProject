package controller.manager;
import java.sql.Connection;
import model.db.DBManager;

public class PostManager {

	private volatile static PostManager instance = null;
	private static Connection con;
	
	private PostManager() {
		con = DBManager.getInstance().getConnection();
	}
	
	public static PostManager getInstance() {
		if (instance == null) {
			synchronized (PostManager.class) {
				if (instance == null) {
					instance = new PostManager();
				}
			}
		}
		return instance;
	}
}
