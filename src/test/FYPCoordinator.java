package test;


import java.util.Scanner;

import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectPrinter;
import databaseRequest.DatabaseRequestAccessor;
import databaseRequest.RequestPrinter;
import databaseUser.databaseUserAccessor;

/**
 * 
 * @author A34_3
 *
 */
public class FYPCoordinator extends Supervisor{

	/**
	 * Constructor
	 * @param name name of FYP Coordinator
	 * @param email email of FYP Coordinator
	 * @param ID ID of FYP Coordinator
	 */
	public FYPCoordinator(String name, String email, String ID) {
		super(name,email,ID);		
	}
	
	/*
	 * Generates project details based on filters
	 */
	private void generateProjectDetails() {
		String choice;
		int valid=0;
			do{
				System.out.println("/1: generate based on status");
				System.out.println("/2: generate based on student id");
				System.out.println("/3: generate based on supervisor id");
				System.out.println("/4: generate based on project id");
				choice = sc.nextLine();
				if(choice.equals("/1") || choice.equals("/2")|| choice.equals("/3")|| choice.equals("/4")){
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
					System.out.println("/4: unavailable projects");
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
					else if(choice.equals("/4")){
						ProjectPrinter.printAllBasedOnStatus(3);
						valid=1;
					}

				}while(valid!=1);

			}
			else if(choice.equals("/2")){
				valid = 0;
				String ID = " ";
				Scanner scan = new Scanner(System.in);
				while(valid == 0) {
			    	  try {
				    	  System.out.println("Enter student ID");
				    	  ID = scan.nextLine();
				    	  databaseUserAccessor.getUser(ID);
				    	  valid = 1;
				    	  }
			    	  catch(Exception e) {
			    		  System.out.println("Invalid ID. Please enter again.");
			    		  }
			    }
				if(databaseUserAccessor.getUser(ID)!=null){
					ProjectPrinter.printAllBasedOnStudent(ID);
				
			}
			else if(choice.equals("/3")){
				System.out.println("test/3");
				int valid1= 0;
				ID = " ";
				scan = new Scanner(System.in);
				while(valid1 == 0) {
			    	  try {
				    	  System.out.println("Enter supervisor ID");
				    	  ID = scan.nextLine();
				    	  databaseUserAccessor.getUser(ID);
				    	  valid1 = 1;
				    	  }
			    	  catch(Exception e) {
			    		  System.out.println("Invalid ID. Please enter again.");
			    		  }
			    }
				if(databaseUserAccessor.getUser(ID)!=null){
					ProjectPrinter.printAllBasedOnSupervisor(ID);
				}
			}
			else if(choice.equals("/4")){
				valid = 0;
				do{
					System.out.println("Enter project id");
					int projectID = sc.nextInt();
					if(projectID>=0 && projectID<=DatabaseProjectAccessor.getSize()){
						ProjectPrinter.printProject(projectID);
						valid=1;
					}
					if(valid==0){
						System.out.println("project id entered was invalid. Please try again.");
					}
				}while(valid!=1);
			}
			}
	}
	
	/**
	 * Displays options menu and indicates "NEW!" when there are pending requests
	 */
	public void displayOptions() {
		System.out.println(" "); //for visibility
	    String choice = "dummy";
	    int check = 0;
	    while (!choice.equals("/0")&&check== 0) {
	      System.out.println("/0: Log out");
		    System.out.println("/1: Change password");
		    System.out.println("/2: View your request history");
		    System.out.print("/3: View pending requests");
		    if(super.getincomingRequest().size() > super.getSettledRequests()) System.out.print(" NEW!");
		    System.out.println();
		    System.out.println("/4: View your projects");
		    System.out.println("/5: Create a new project");
		    System.out.println("/6: Update project title");
		    System.out.println("/7: Settle requests");
		    System.out.println("/8: Request project transfer");
		    System.out.println("/9: View all requests in system");
		    System.out.println("/10: View all projects in system");
		    System.out.println("/11: Generate project details");

		    Scanner sc = new Scanner(System.in);
		    choice = sc.nextLine();
		    if(choice.equals("/1")) {setPassword();check = 1;}
		    else if(choice.equals("/2")) {super.viewAllRequests();}
		    else if(choice.equals("/3")) {super.viewPendingRequests();}
		    else if(choice.equals("/4")) {super.viewAllProjects();}
		    else if(choice.equals("/5")) {super.createProject();}
		    else if(choice.equals("/6")) {super.changeTitle();}
		    else if(choice.equals("/7")) {super.settleRequests();}
		    else if(choice.equals("/8")) {super.transfer();}
		    else if(choice.equals("/9")) {
		      //EVERYTHING IN DATABASE
		      for(int i=0; i<DatabaseRequestAccessor.getSize(); i++) {
			RequestPrinter.printRequest(i);
		      }
		    }
		    else if(choice.equals("/10")) {
		      for(int i=0; i<DatabaseProjectAccessor.getSize(); i++) {
			ProjectPrinter.printProject(i);
		      }
		    }
		    else if(choice.equals("/11")) {generateProjectDetails();}
		    else if(choice.equals("/0")) {System.out.println("Logging out...");}

		    else {System.out.println("Invalid option");}
		}
	    
	  }
}