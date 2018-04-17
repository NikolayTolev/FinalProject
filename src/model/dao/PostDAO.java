package model.dao;

public class PostDAO {

	private volatile static PostDAO instance = null;
	
	private PostDAO() {}
	
	public static PostDAO getInstance() {
		if(instance == null) {
			synchronized (PostDAO.class) {
				if(instance == null) {
					instance = new PostDAO();
				}
			}
		}
		return instance;
	}
}
