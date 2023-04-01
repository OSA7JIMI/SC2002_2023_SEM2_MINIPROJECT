package test;

import java.util.Scanner;
import request.Request;

public abstract class User {
	private String userID;
	private String password ="password";
	private String name;
	private String email;
	
	public User(name, email, String ID) {
		userID = ID;
		this.name = name;
		this.email = email;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword() {
		Scanner a = new Scanner(System.in);
		System.out.println("Please enter your new password: ");
		password = a.nextLine();
	}	
	
	public abstract void viewAllProjects();
	public abstract void displayOptions(); 
	
	public abstract void viewPendingRequests();
	public abstract void viewAllRequests();
