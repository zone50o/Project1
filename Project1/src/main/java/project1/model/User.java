package project1.model;

public class User {
	
	private static int nextID = 0;
	private int id;
	private String username;
	private String password;
	private String fullName;
	private String email;
	private String role;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String username, String password, String fullName, String email,
			int roleID) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.role = getRoleByInt(roleID);
	}

	public User(String username, String password, String fullName, String email, int roleID) {
		super();
		this.id = ++nextID;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.role = getRoleByInt(roleID);
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public String getFullName() {return fullName;}
	public void setFullName(String fullName) {this.fullName = fullName;}


	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public String getRole() {
		return role;
	}
	
	public String getRoleByInt(int roleID) {
		if(roleID==1) {
			return "Employee";
		}else if(roleID==2) {
			return "Finance Manager";
		}
		return "Error";
	}
	public void setRoleByInt(int roleID) {
		if(roleID==1) {
			this.role = "Employee";
		}else if(roleID==2) {
			this.role = "Finance Manager";
		}
		this.role = "Error";
	}

	public static void setNextID(int nextID) {User.nextID = nextID;}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullName=" + fullName
				+ ", email=" + email + ", role=" + role + "]";
	}

	
}
