package test;

import java.util.Scanner;


/**
 * 
 * @author A34_3
 * @version 9 Apr 2023
 */
public abstract class User {
	private String userID;
	private String password ="password";
	private String name;
	private String email;
	/**
	 * All users should know who the FYP Coordinator is
	 */
	public  static FYPCoordinator FYPcoor = null;
	
	User(String name,String email, String ID) {
		userID = ID;
		this.name = name;
		this.email = email;
	}
	
	/**
	 * Setter
	 * @param FYPcoor FYP Coordinator object
	 */
	public static void setFYPcoor(FYPCoordinator FYPcoor) {
		User.FYPcoor = FYPcoor;
	}
	
	/**
	 * Getter
	 * @return ID of user
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Getter
	 * @return name of user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter
	 * @return email address of user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Getter
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Change password
	 */
	public void setPassword() {
		Scanner a = new Scanner(System.in);
		System.out.println("Please enter your new password: ");
		password = a.nextLine();
		System.out.println("Password changed succesfully! Please log in again");
	}	
	
	/**
	 * Change password #2
	 * @param newPassword new password
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	protected abstract void viewAllProjects();
	/**
	 * Displays options menu. Implemented in child classes
	 */
	public abstract void displayOptions(); 
	
	protected abstract void viewPendingRequests();
	protected abstract void viewAllRequests();
	/**
	 * Update incoming request array
	 * @param index index of request in local database
	 */
	public abstract void addToIncomingRequest(int index);
	/**
	 * Update outgoing request array
	 * @param index index of request in local database
	 */
	public abstract void addToOutgoingRequest(int index);
}