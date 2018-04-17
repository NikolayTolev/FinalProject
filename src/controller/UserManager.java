package controller;

public class UserManager {

	private volatile static UserManager instance = null;
	
	private UserManager() {}
	
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
