package model;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String country;
	private String biography;
	private String photo;
	
	public User(String firstName, String lastName, String username, String password, String email, String country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.country = country;
	}

	public User(int id, String firstName, String lastName, String username, String password, String email,
			String country) {
		this(firstName, lastName, username, password, email, country);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getCountry() {
		return country;
	}
	
	
}