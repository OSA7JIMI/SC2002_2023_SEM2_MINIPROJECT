package Test;

import java.util.ArrayList;

import java.util.Scanner;

import Request.Request;
import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectPrinter;
import Request.*;
import databaseRequest.DatabaseRequestAccessor;
import databaseRequest.RequestPrinter;

public class FYPCoordinator extends Supervisor{
	

	public FYPCoordinator(String name, String email, String ID) {
		super(name,email,ID);		
	}
	public void settleRequests(){
		if(super.getincomingRequest().size()==0) {
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
	
	public void displayOptions() {
		System.out.println("Enter /1 to change your password");
		System.out.println("Enter /2 to view your requests");
		System.out.println("Enter /3 to view your pending requests");
		System.out.println("Enter /4 to view all requests");
		System.out.println("Enter /5 to view your projects");
		System.out.println("Enter /6 to view all projects");
		System.out.println("Enter /7 to settle requests");
		System.out.println("Enter /8 to generate project details of a project");
			
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
			//EVERYTHING IN DATABASE
			for(int i=0; i<DatabaseRequestAccessor.getSize(); i++) {
				RequestPrinter.printAllRequest(i);
			}
		}
		else if(choice.equals("/5")) {
			super.viewAllProjects();
		}
		else if(choice.equals("/6")) {
			for(int i=0; i<DatabaseProjectAccessor.getSize(); i++) {
				ProjectPrinter.printALlProject(i);
			}
		}
		else if(choice.equals("/7")) {
			settleRequests();

		}
		else if(choice.equals("/8")) {
			int valid=0;
			do{
				System.out.println("Enter /1 to generate based on status");
				System.out.println("Enter /2 to generate based on supervisorId");
				choice = sc.nextLine();
				if(choice.equals("/1") || choice.equals("/2")){
					valid=1;
				}
			}while(valid!=1);
			if(choice.equals("/1")){
				valid=0;
				do{
					System.out.println("Enter status of project:");
					System.out.println("Enter /1 for available projects");
					System.out.println("Enter /2 for reserved projects");
					System.out.println("Enter /3 for taken projects");
					choice = sc.nextLine();
					if(choice.equals("/1")){
						ProjectPrinter.printAllBasedOnStatus(0);
						valid=1;
					}
					else if(choice.equals("/2")){
						ProjectPrinter.printAllBasedOnStatus(1);
						valid=1;
					}
					else if(choice.equals("/3")){
						ProjectPrinter.printAllBasedOnStatus(2);
						valid=1;
					}
					   
				}while(valid!=1);
				
			}
			else if(choice.equals("/2")){
				//not implemented yet
			}
		}
	}
}