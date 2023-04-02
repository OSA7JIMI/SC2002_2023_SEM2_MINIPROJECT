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
	
	private generateProjectDetails() {
		int valid=0;
			do{
				System.out.println("/1: generate based on status");
				System.out.println("/2: generate based on student id");
				System.out.println("/3: generate based on supervisor id");
				System.out.println("/4: generate based on project id");
				choice = sc.nextLine();
				if(choice.equals("/1") || choice.equals("/2")){
					valid=1;
				}
			}while(valid!=1);
			if(choice.equals("/1")){
				valid=0;
				do{
					System.out.println("Enter status of project:");
					System.out.println("/1: available projects");
					System.out.println("/2: reserved projects");
					System.out.println("/3: taken projects");
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
				valid = 0;
				do{
					System.out.println("Enter student id");
					int studentid = sc.nextLine();
					if(getUser(studentid)!=null){
						ProjectPrinter.printAllBasedOnStudent(studentid);
						valid=1;
					}
					if(valid==0){
						System.out.println("student id entered was invalid. Please try again.");
					}
				}while(valid!=1);
			}
			else if(choice.equals("/3")){
				valid = 0;
				do{
					System.out.println("Enter supervisor id");
					string supervisorid = sc.nextLine();
					if(getUser(supervisorid)!=null){
						ProjectPrinter.printAllBasedOnSupervisor(supervisorid);
						valid=1;
					}
					if(valid==0){
						System.out.println("supervisor id entered was invalid. Please try again.");
					}
				}while(valid!=1);
			}
			else if(choice.equals("/4")){
				valid = 0;
				do{
					System.out.println("Enter project id");
					int projectID = sc.nextInt();
					if(projectID>=0 && projectID<=DatabaseProjectAccessor.getsize()){
						ProjectPrinter.printAllProject(projectID);
						valid=1;
					}
					if(valid==0){
						System.out.println("project id entered was invalid. Please try again.");
					}
				}while(valid!=1);
			}
	}
	
	
	public void displayOptions() {
		String choice = "dummy";
		
		while (!choice.equals("/0")) {
			System.out.println("/0: Log out");
			System.out.println("/1: Change password");
			System.out.println("/2: View your request history");
			System.out.print("/3: View pending requests");
			if(incomingRequest.size() > super.getSettledRequests()) System.out.print(" NEW!");
			System.out.println();
			System.out.println("/4: View all requests in system");
			System.out.println("/5: View your projects");
			System.out.println("/6: View all projects in system");
			System.out.println("/7: Settle requests");
			System.out.println("/8: Generate project details");

			choice = sc.nextLine();
			if(choice.equals("/1")) {setPassword();}
			else if(choice.equals("/2")) {super.viewAllRequests();}
			else if(choice.equals("/3")) {super.viewPendingRequests();}
			else if(choice.equals("/4")) {
				//EVERYTHING IN DATABASE
				for(int i=0; i<DatabaseRequestAccessor.getSize(); i++) {
					RequestPrinter.printAllRequest(i);
				}
			}
			else if(choice.equals("/5")) {super.viewAllProjects();}
			else if(choice.equals("/6")) {
				for(int i=0; i<DatabaseProjectAccessor.getSize(); i++) {
					ProjectPrinter.printAllProject(i);
				}
			}
			else if(choice.equals("/7")) {super.settleRequests();}
			else if(choice.equals("/8")) {generateProjectDetails();}
				
		}
	}
}
