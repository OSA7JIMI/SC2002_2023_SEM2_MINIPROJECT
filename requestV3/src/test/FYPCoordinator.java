package OOP_Group_Project;

import java.util.ArrayList;
import java.util.Scanner;

import databaseProject.DatabaseProjectAccessor;
import request.*;
import databaseRequest.DatabaseRequestAccessor;
import databaseRequest.RequestPrinter;

public class FYPCoordinator extends Supervisor{
	

	public FYPCoordinator(String name, String email, String ID) {
		super(name,email,ID);		
	}
	public void settleRequests(){
		if(super.getincomingRequest().size() == 0) {
			System.out.println("There's no incoming request!");
		}
		System.out.println("Enter the student ID to settle his/her request");
		String stuID = sc.next();
		int i, ID = 0;
		for(i = 0; i < super.getincomingRequest().size();i++) {
			if(DatabaseRequestAccessor.getRequest(super.getincomingRequest().get(i)).senderID == stuID) {
				ID = super.getincomingRequest().get(i);
				break;
		    }
	    }
		if(ID == 0) {
			System.out.println("This student didn't make request to you");
		}
		else {
			Request r = DatabaseRequestAccessor.getRequest(ID);
			System.out.println("Enter 0 to reject, 1 to approve");
			int choice = sc.nextInt();
			if(choice==0) {
				r.settleRequest(false);
			}
			else {
				r.settleRequest(true);
			}
		}
	}
	
	public void generateProjectDetails(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		System.out.println("projectID: " p.getprojectID());
		System.out.println("project title: " p.getprojectTitle());
		System.out.println("Status: " p.getstatus());
		
	}
	
	public void displayOptions() {
		System.outprintln("Enter /1 to change your password");
		System.outprintln("Enter /2 to view your requests");
		System.outprintln("Enter /3 to view your pending requests");
		System.outprintln("Enter /4 to view all requests");
		System.outprintln("Enter /5 to view your projects");
		System.outprintln("Enter /6 to view all projects");
		System.outprintln("Enter /7 to settle requests");
		System.outprintln("Enter /8 to generate project details of a project");
			
		String choice = sc.nextLine();
		if(choice.equals("/1")) {
			setPassword();
		}
		else if(choice.equals("/2")) {
			super.viewAllRequests();
		}
		else if(choice.equals("/3")) {
			super.viewPendingRequests();
		}
		else if(choice.equals("/4")) {
			DatabaseRequestAccessor.viewAllRequests();
		}
		else if(choice.equals("/5")) {
			super.viewAllProjects();
		}
		else if(choice.equals("/6")) {
			DatabaseProjectAccessor.viewAllProjects();
		}
		else if(choice.equals("/7")) {
			settleRequests();

		}
		else if(choice.equals("/8")) {
			int valid=0;
			do{
				System.outprintln("Enter /1 to generate based on status");
				System.outprintln("Enter /2 to generate based on supervisorId");
				choice = sc.nextLine();
				if(choice.equals("/1") || choice.equals("/2)){
					valid=1;
				}
			}while(valid!=1);
			if(choice.equals("/1")){
				valid=0;
				do{
					System.outprintln("Enter status of project:");
					System.outprintln("Enter /1 for available projects");
					System.outprintln("Enter /2 for reserved projects");
					System.outprintln("Enter /3 for taken projects");
					choice = sc.nextLine();
					if(choice.equals("/1"){
						Projectprinter.printBasedOnStatus(0);
						valid=1;
					}
					else if(choice.equals("/2"){
						Projectprinter.printBasedOnStatus(1);
						valid=1;
					}
					else if(choice.equals("/3"){
						Projectprinter.printBasedOnStatus(2);
						valid=1;
					}
					   
				}while(valid!=1);
				
			}
			else if(choice.equals("/2"){
				
			}
}
