package Test;

import java.util.Scanner;
import Request.Request;
import databaseRequest.DatabaseRequestAccessor;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class User {
	private String userID;
	private String password ="password";
	private String name;
	private String email;
	public  static FYPCoordinator FYPcoor = null;
	
	User(String name,String email, String ID) {
		userID = ID;
		this.name = name;
		this.email = email;
	}
	
	public static void setFYPcoor(FYPCoordinator FYPcoor) {
		User.FYPcoor = FYPcoor;
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
		System.out.println("Password changed succesfully");
	}	
	
	public abstract void viewAllProjects();
	public abstract void displayOptions(); 
	
	public abstract void viewPendingRequests();
	public abstract void viewAllRequests();
	public abstract void addToIncomingRequest(int index);
}
