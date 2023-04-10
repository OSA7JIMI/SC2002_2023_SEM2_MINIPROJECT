package test;
import java.util.ArrayList;


import java.util.Scanner;
import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectPrinter;
import databaseRequest.DatabaseRequestAccessor;
import databaseRequest.RequestPrinter;
import databaseUser.databaseUserAccessor;
import Request.Request;
import Request.RequestAllocate;
import Request.RequestChangeTitle;
import Request.RequestDeregister;

/**
 * The Student class represents a student user in the FYP management system.
 * This class inherits from the User class and adds properties specific to students,
 * such as their project ID, deregistration status, and lists of incoming and outgoing requests.
 * 
 * @author A34_3
 * @version 9 Apr 2023
 */
public class Student extends User {
	private int projectID = -1;
	private boolean deregistered = false;
	
	private ArrayList<Integer> incomingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> outgoingRequest = new ArrayList<Integer>();
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructor
	 * @param name name of student
	 * @param email email address of student
	 * @param ID student ID
	 */
	public Student(String name, String email, String ID) {
		super(name, email, ID); 
	}
	
	/**
	 * Setter
	 * @param projectID index of project in local array
	 */
	public void setProject(int projectID) {
		this.projectID = projectID;
	}
	
	/**
	 * Getter
	 * @return index of project in local array
	 */
	public int getProjectID() {
		return projectID;
	}
	
	/**
	 * Record that the student has deregistered from FYP
	 */
	public void setDeregister() {
		deregistered = true;
		projectID = -1;
	}
	
	/**
	 * Check if the student has deregistered from FYP
	 * @return deregistration status
	 */
	public boolean getDeregister() {
		return deregistered;
	}
	
	protected void viewAllProjects() {
		ProjectPrinter.printAllAvailable();
	}
	
	
	private void viewProject() {		
		Project p = DatabaseProjectAccessor.getProject(projectID);
		Supervisor sp = (Supervisor) databaseUserAccessor.getUser(p.getSupervisorID());
		System.out.println("Your project title is: " + p.getTitle());
		System.out.println("Your supervisor is: " + sp.getName());
	}
	
	private void changeTitle() {
		System.out.println("Enter new title");
		String newTitle = sc.nextLine();
		Request r = new RequestChangeTitle(newTitle);
		Project p = DatabaseProjectAccessor.getProject(projectID);
		r.setsenderID (getUserID());
		r.setreceiverID(p.getSupervisorID());
		r.setrequestIndex(DatabaseRequestAccessor.getSize());
		DatabaseRequestAccessor.addRequest(r);
		outgoingRequest.add(r.getrequestIndex());
		Supervisor sp = (Supervisor) databaseUserAccessor.getUser(DatabaseProjectAccessor.getProject(projectID).getSupervisorID());
		sp.addToIncomingRequest(r.getrequestIndex());
		System.out.println("Title change request sent");
	}
	
	private void allocate() {
		if(!deregistered) {
			if(DatabaseProjectAccessor.getSize()==0) {
				System.out.println("No projects created");
				return;
			}
			System.out.println("Enter projectID to be allocated");
			
			int projectID ;
			try {
				projectID = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e) {
				System.out.println("Invalid input");
				return;
			}
			
			Request r = new RequestAllocate(projectID);
			r.setrequestIndex(DatabaseRequestAccessor.getSize());
			r.setsenderID (getUserID());
			r.setreceiverID(FYPcoor.getUserID()); 
			int index = DatabaseRequestAccessor.addRequest(r);
			incomingRequest.add(index);
			FYPcoor.addToIncomingRequest(index);
			System.out.println("Allocation request sent");
		}
		else {
			System.out.println("Since you have previously deregistered a project, you are not allowed to register for another one.");
		}
		
	}
	
	private void deregister() {
		System.out.println("Deregistration request sent");
		Request r = new RequestDeregister();
		r.setrequestIndex(DatabaseRequestAccessor.getSize());
		r.setsenderID (getUserID());
		r.setreceiverID (FYPcoor.getUserID());
		int index = DatabaseRequestAccessor.addRequest(r);
		outgoingRequest.add(index);
		FYPcoor.addToIncomingRequest(index);
	}
	
	public void displayOptions() { //Student
		System.out.println(" "); //for visibility
	    String choice = "dummy";
	    int check = 0;
	    
	    while (!choice.equals("/0") && check == 0) {
	      System.out.println("/0: Log out");
	      System.out.println("/1: Change password");
	      System.out.println("/2: View request history");
	      System.out.println("/3: View pending requests");
	      if (projectID==-1) {
	        System.out.println("/4: View all available projects");
	        System.out.println("/5: Request project allocation");
	        choice = sc.nextLine();
	        if (choice.equals("/1")) {setPassword();check = 1;}
	        else if(choice.equals("/2")) {viewAllRequests();}
	        else if(choice.equals("/3")) {viewPendingRequests();}
	        else if (choice.equals("/4")) viewAllProjects();
	        else if (choice.equals("/5")) allocate();
	        else if(choice.equals("/0")) {System.out.println("Logging out...");}
	        else {System.out.println("Invalid option");}
	      }
	      else {
	        System.out.println("/4: View your project");
	        System.out.println("/5: Request to change your project title");
	        System.out.println("/6: Request project deregistration");
	        choice = sc.nextLine();
	        if (choice.equals("/1")) {setPassword();check = 1;}
	        else if(choice.equals("/2")) {viewAllRequests();}
	        else if(choice.equals("/3")) {viewPendingRequests();}
	        else if(choice.equals("/4")) {viewProject();}
	        else if(choice.equals("/5")) {changeTitle();}
	        else if(choice.equals("/6")) {deregister();}
	        else if(choice.equals("/0")) {System.out.println("Logging out...");}
	        else {System.out.println("Invalid option");}  
	      }
	    }
	  }

	protected void viewPendingRequests(){
		System.out.println("---Pending requests---");
		System.out.println("---Incoming requests---");
		for(int i=0; i<incomingRequest.size(); i++) {
			RequestPrinter.printAllPendingRequest(incomingRequest.get(i));
		}
		System.out.println("---Outgoing requests---");
		for(int i=0; i<outgoingRequest.size(); i++) {
			RequestPrinter.printAllPendingRequest(outgoingRequest.get(i));
		}
	}

	protected void viewAllRequests() {
		System.out.println("---Request history---");
		System.out.println("---Incoming requests---");
		for(int i=0; i<incomingRequest.size(); i++) {
			RequestPrinter.printRequest(incomingRequest.get(i));
		}
		System.out.println(" ");
		System.out.println("---Outgoing requests---");
		for(int i=0; i<outgoingRequest.size(); i++) {
			RequestPrinter.printRequest(outgoingRequest.get(i));
		}
		
	}
						  
	public void addToIncomingRequest(int index) {
		incomingRequest.add(index);
	}
	public void addToOutgoingRequest(int index) {
		outgoingRequest.add(index);
	}
						  
}