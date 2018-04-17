package controller.manager;

public class CommentManager {

	private volatile static CommentManager instance = null;
	
	private CommentManager() {}
	
	public static CommentManager getInstance() {
		if(instance == null) {
			synchronized (CommentManager.class) {
				if (instance == null) {
					instance = new CommentManager();
				}
			}
		}
		return instance;
	}
}
