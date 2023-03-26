package test;

import java.util.Scanner;
import databaseProject.*;
import databaseRequest.*;
import UserArray.*;

public class FYPCoordinator extends Supervisor{

	public FYPCoordinator(String name, String email, String ID) {
		super(name, email, ID);
		this.isFYP = true;
		
	}
	public void settleRequests(){
		int i=0,j=1;
		Scanner sc = new Scanner(System.in);
		for(i=0;i<incomingRequest.size();i++) {
			if(DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).pending == true) {
				System.out.println("Request " + j + ": " + "type: " + DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).getType() + 
						"sender: " + DatabaseUserAccessor.getUser(DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).senderID)+
						"Receiver: " + DatabaseUserAccessor.getUser(DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).receiverID);
				j++;
			}
			
		}
		System.out.println("Enter request number or -1 to exit");
		int choice = sc.nextInt();
		//tbc
		
		
		
	}
	public void viewAllRequests() {
		
	}
	public void viewAllProjects() {
		DatabaseProjectAccessor.viewAllProject();;
	}
	public void generateProjectDetails(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		System.out.println("projectID: " + p.getID());
		System.out.println("project title: "  + p.getTitle());
		System.out.println("Status: " + p.getStatus());
		
	}
	
}
		
