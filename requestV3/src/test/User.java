package test;

import java.util.Scanner;
import request.Request;
import java.util.LinkedList;

public abstract class User {
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword() {
		Scanner a = new Scanner(System.in);
		System.out.println("Please enter your new password: ");
		password = a.nextLine();
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public void setEmail(String email) {
		this.email = email;
	}	
	
	public abstract void viewAllProjects();
	public abstract void displayOptions(); 
	
	
	public void viewAllRequests() {
		System.out.println("Request History: \n");
		for(int i=0; i<incomingRequest.size(); i++) {
			Request r = DatabaseRequestAccessor.getRequest(incomingRequest.get(i));
			System.out.println("Sender: "+r.senderID);
			System.out.println("Type: "+r.type);
			if(r.pending) {System.out.println("Status: Pending");}
			else if(r.approval) {System.out.println("Status: Approved");}
			else {System.out.println("Status: Rejected");}
		}
		
		for(int i=0; i<outgoingRequest.size(); i++) {
			Request r = DatabaseRequestAccessor.getRequest(outgoingRequest.get(i));
			System.out.println("Receiver: "+r.receiverID);
			System.out.println("Type: "+r.type);
			if(r.pending) {System.out.println("Status: Pending");}
			else if(r.approval) {System.out.println("Status: Approved");}
			else {System.out.println("Status: Rejected");}		
		}
	} 
	
}
