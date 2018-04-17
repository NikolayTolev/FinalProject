package controller;

public class PostManager {

	private volatile static PostManager instance = null;
	
	private PostManager() {}
	
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
