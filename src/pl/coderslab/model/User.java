package pl.coderslab.model;


public class User {

	// POLA KLASY
	private Integer id;
	private String name;
	private String email;
	private String password;
	private Integer userGroupId;

	// KONSTRUKTOR
	public User(Integer id, String name, String email, String password, Integer userGroupId) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.userGroupId = userGroupId;
	}
	
	

	public User(String name, String email, String password, Integer userGroupId) {
		this.name = name;
		this.email = email;
		this.userGroupId = userGroupId;
		this.password = password;
	}


	// KONSTRUKTOR BEZPARAMETROWY
	public User() {

	}

	// GETTERY SETTERY
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void readPassword(String password) {
		this.password = password;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}


	// TO STRING
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password="
				+ password + ", userGroupId= " + userGroupId + "]+\n";
	}

}
