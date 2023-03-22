package test;

import java.util.Scanner;
import request.Request;
import java.util.LinkedList;

public abstract class User {
	private String userID;
	private String password;
	private String name;
	private String email;
	private LinkedList <Request> incoming;
	private LinkedList<Request> outgoing;
	
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
	
	public abstract void viewAllProjects() 
	public abstract void changeTitle() 
	public abstract void displayOptions() 
	
	public void updateIncomingRequest(int requestID, boolean approval) {		
	}
	
	public void updateOutgoingRequest(int requestID, boolean approval) {
	}
	
	public void viewAllRequests() {
		System.out.println("Request History: \n");
		for(int i=0; i<incoming.size(); i++) {
			System.out.println("Sender: "+incoming.get(i).senderID);
			System.out.println("Type: "+incoming.get(i).type);
			if(incoming.get(i).pending) {System.out.println("Status: Pending");}
			else if(incoming.get(i).approval) {System.out.println("Status: Approved");}
			else {System.out.println("Status: Rejected");}
		}
		
		for(int i=0; i<outgoing.size(); i++) {
			System.out.println("Receiver: "+outgoing.get(i).receiverID);
			System.out.println("Type: "+outgoing.get(i).type);
			if(outgoing.get(i).pending) {System.out.println("Status: Pending");}
			else if(outgoing.get(i).approval) {System.out.println("Status: Approved");}
			else {System.out.println("Status: Rejected");}		
		}
	} 
	
}
