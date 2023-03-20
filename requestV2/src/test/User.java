package test;

import java.util.Scanner;

import request.Request;

public class User {
	private String userID;
	private String password;
	private String name;
	private String email;
	
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
	//NEW
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	 public void viewAllProjects() {
	}
	 public void viewAllRequests() {
	}
	 public void changeTitle() {
	}
	 public void displayOptions() {
	}
	 public void updateOutgoingRequest(int senderIndex, boolean approval) {
	}
}