package controller.manager;
import java.sql.Connection;
import model.db.DBManager;

public class CommentManager {

	private static volatile CommentManager instance = null;
	private static Connection con;
	
	private CommentManager() {
		con = DBManager.getInstance().getConnection();
	}
	
	public static CommentManager getInstance() {
		if (instance == null) {
			synchronized (CommentManager.class) {
				if (instance == null) {
					instance = new CommentManager();
				}
			}
		}
		return instance;
	}
}
