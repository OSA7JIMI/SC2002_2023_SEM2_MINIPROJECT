package sc2002_assignment;

import java.util.Scanner;
import java.util.LinkedList;

public abstract User {
	private String userID;
	private String password;
	private String name;
	private String email;
	private LinkedList<Request>;
	
	User(String ID) {
		userID = ID;
		password = "password";
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword() {
		Scanner a = new Scanner(System.in);
		System.out.println("Please enter your new password: ");
		password = a.nextLine();
	}
	
	abstract public void viewAllProjects();
	abstract public void viewAllRequests();
	abstract public void changeTitle();
	abstract public void displayOptions();
	
}
