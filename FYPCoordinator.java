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
	public void displayOptions() {
		int leave =0;
		Scanncer sc = new Scanner(System.in);
		while(!leave) {
			System.outprintln("Enter /leave to leave FYP coordinator options");
			System.outprintln("Enter /viewrequests to view all requests");
			System.outprintln("Enter /viewprojects to view all projects");
			System.outprintln("Enter /settlerequests to settle requests");
			System.outprintln("Enter /projectdetails to generate project details of a project");
			String choice = sc.nextline();
			if(choice.equals("/leave")) {
				leave=1;
			}
			else if(choice.equals("/viewrequests")) {
				this.viewAllRequests();
			}
			else if(choice.equals("/viewprojects")) {
				this.viewAllProjects();
			}
			else if(choice.equals("/projectdetails")) {
				System.out.println("Enter project id:");
				do {
					projectid = sc.nextInt();
				}while(projectid < 1 || projectid > DatabaseProjectAccessor.getsize());
				this.generateProjectDetails(projectid); // Need to minus 1 or not?
			}
			else {
				System.out.println("Invalid option chosen. Please try again.");
			}
		}
	}
						   
	public void viewAllRequests() {
		DatabaseProjectAccessor.viewAllRequests();
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
		
