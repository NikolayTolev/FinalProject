package model;

public class CommentDAO {

	private volatile static CommentDAO instance = null;
	
	private CommentDAO() {}
	
	public static CommentDAO getInstance() {
		if(instance == null) {
			synchronized (CommentDAO.class) {
				if(instance == null) {
					instance = new CommentDAO();
				}
			}
		}
		return instance;
	}
}
