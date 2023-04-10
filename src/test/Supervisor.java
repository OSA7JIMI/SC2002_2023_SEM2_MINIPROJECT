package test;
import java.util.ArrayList;

import java.util.Scanner;

import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectPrinter;
import databaseRequest.DatabaseRequestAccessor;
import databaseRequest.RequestPrinter;
import databaseUser.databaseUserAccessor;
import Request.Request;
import Request.RequestTransfer;

/**
 * The Supervisor class represents a supervisor user in the FYP management system.
 * This class inherits from the User class and adds properties specific to supervisors,
 * such as the number of projects they are supervising, the number of projects they have
 * created, and lists of incoming and outgoing requests.
 * 
 * @author A34_3
 * @version 9 Apr 2023
 */
public class Supervisor extends User{
	private int numProject = 0;
	private int numProjectCreated = 0;
	private int settledRequests = 0;
	private ArrayList<Integer> incomingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> outgoingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> projectArray = new ArrayList<Integer>();
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructor
	 * @param name name of supervisor
	 * @param email email address of supervisor
	 * @param ID Supervisor ID
	 */
	public Supervisor(String name, String email, String ID) {
		super(name, email, ID);
	}
	
	/**
	 * Record that supervisor settled a request
	 */
	public void incrementSettled() {
		settledRequests++;
	}
	
	/**
	 * Record that supervisor is supervising one less project
	 */
	public void decrementNumProject() {
		numProject--;
	}
	/**
	 * Record that supervisor is supervising one more project
	 */
	public void incrementNumProject() {
		numProject++;
	}
	
	/**
	 * Record that supervisor created a new project
	 */
	public void incrementNumProjectCreated() {
		numProjectCreated++;
	}
	
	/**
	 * Getter
	 * @return the list of projects belonging to supervisor
	 */
	public ArrayList<Integer> getProjectArray() {
		return projectArray;
	}
	/**
	 * Getter
	 * @return the list of incoming requests
	 */
	public ArrayList<Integer> getincomingRequest() {
		return incomingRequest;
	}
	/**
	 * Getter
	 * @return the list of outgoing requests
	 */
	public ArrayList<Integer> getoutgoingRequest() {
		return outgoingRequest;
	}
	
	/**
	 * Getter
	 * @return number of projects supervised 
	 */
	public int getNumProject() {
		return numProject;
	}
	
	/**
	 * Getter
	 * @return number of requests settled
	 */
	public int getSettledRequests(){
		return settledRequests;
	}
		
	//NEW
	public void addToIncomingRequest(int index) {
		incomingRequest.add(index);
	}
	
	//NEW
	protected void settleRequests() {
		int ID = -1;
		if(incomingRequest.size() == 0) {
			System.out.println("There's no incoming request!");
			return;
		}
		else { 
			int checkhere = 0;
		      String sID = "ID";
		      while(checkhere == 0) {
		    	  try {
		    		  System.out.println("Enter the sender ID to settle his/her request");
			    	  sID = sc.nextLine();
			    	  databaseUserAccessor.getUser(sID);
			    	  checkhere = 1;
			    	  }
		    	  catch(Exception e) {
		    		  System.out.println("Invalid ID. Please enter again.");
		    		  }
		    	  }
			
			int i;
			for(i = 0; i < incomingRequest.size();i++) {
				if(DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).getsenderID().equals(sID) && DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).getpending()==true) {
					ID = incomingRequest.get(i);
					break;
			    	}
			}
		}
		if(ID == -1) {
			System.out.println("This user didn't make request to you");
		}
		else {
			Request r = DatabaseRequestAccessor.getRequest(ID);
			System.out.println("Enter 0 to reject, 1 to approve");
			
			int choice;
			try {
				choice = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e) {
				System.out.println("Invalid input");
				return;
			}
			
			if(choice==0) {
				r.settleRequest(false);
				System.out.println("Request rejected");
			}
			else {
				r.settleRequest(true);
				System.out.println("Request approved");
			}
		}
		settledRequests++;
	}
	
	//NEW
	protected void createProject() {
		System.out.println("Enter project title");
		String title = sc.nextLine();
		Project p = new Project(DatabaseProjectAccessor.getSize(), title, 0, getUserID());
		incrementNumProjectCreated();
		
		if(numProject>=2) {
			System.out.println("Project limit reached, status set to unavailable");
			p.setStatus(3);
		}
		
		DatabaseProjectAccessor.addProject(p);
		projectArray.add(DatabaseProjectAccessor.getSize()-1);
	}
	protected void viewAllProjects() {	
		if(numProjectCreated==0) {
			System.out.println("You have not created any projects yet");
			return;
		}
		ProjectPrinter.printAllBasedOnSupervisor(this.getUserID());
		
	}

	protected void  transfer() {
		if(numProjectCreated==0) {
			System.out.println("No projects to transfer");
			return;
		}
		System.out.println("Please enter the projectID you want to transfer to another supervisor: ");
		
		int projectID;
		try {
			projectID = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e) {
			System.out.println("Invalid input");
			return;
		}
		
		Project p = DatabaseProjectAccessor.getProject(projectID);
		if(!p.getSupervisorID().equals(getUserID())) {
			System.out.println("This project was not created by you");
			return;
		}
		
		
		int checkhere = 0;
		String replacementID = " ";
		while(checkhere == 0) {
			try {
				System.out.println("Please enter the replacement supervisor ID: ");
				replacementID = sc.nextLine();
				databaseUserAccessor.getUser(replacementID);
				checkhere = 1;
			}
			catch(Exception e) {
				System.out.println("Invalid ID. Please enter again.");
			}
		}
		
		Request r = new RequestTransfer(projectID, replacementID);
		r.setrequestIndex(DatabaseRequestAccessor.getSize());
		r.setsenderID(getUserID());
		r.setreceiverID (User.FYPcoor.getUserID());
		int index = DatabaseRequestAccessor.addRequest(r);
		outgoingRequest.add(index);
		//will change the line below after userarray is implemented
		FYPcoor.addToIncomingRequest(index);
		System.out.println("Transfer request sent");
	}
	
	protected void viewAllRequests() {
		System.out.println("---Request history---");
		System.out.println("---Incoming requests---");
		for(int i=0; i<incomingRequest.size(); i++) {
			RequestPrinter.printRequest(incomingRequest.get(i));
		}
		System.out.println("---Outgoing requests---");
		for(int i=0; i<outgoingRequest.size(); i++) {
			RequestPrinter.printRequest(outgoingRequest.get(i));
		}
	}

	protected void changeTitle() {
		if(DatabaseProjectAccessor.getSize()==0) {
			System.out.println("No projects created");
			return;
		}
		
		System.out.println("Enter the project id: ");
		Scanner sc = new Scanner(System.in);
		
		int id;
		try {
			id = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e) {
			System.out.println("Invalid input");
			return;
		}
		
		Project p = DatabaseProjectAccessor.getProject(id);
		
		if(p==null) {
			System.out.println("Invalid project ID");
			return;
		}
		else if(!p.getSupervisorID().equals(getUserID())) {
			System.out.println("This project was not created by you");
			return;
		}
		
		System.out.println("Enter the new title: ");
		String newTitle = sc.nextLine();
		p.setTitle(newTitle);
		DatabaseProjectAccessor.updateProjectInDatabase(p);
	}
	
	public void displayOptions() {
		System.out.println();//for visibility
	    String choice = "dummy";
	    int check = 0;
	    while (!choice.equals("/0") && check == 0) {
	      System.out.println("/0: Log out");
		    System.out.println("/1: Change password");
		    System.out.println("/2: View your request history");
		    System.out.print("/3: View pending requests");
		    if(incomingRequest.size() > settledRequests) System.out.print(": NEW!");
		    System.out.println();
		    System.out.println("/4: View your projects");
		    System.out.println("/5: Create a new project");
		    System.out.println("/6: Update project title");
		    System.out.println("/7: Settle requests");
		    System.out.println("/8: Request project transfer");

		    choice = sc.nextLine();
		    if(choice.equals("/1")) {setPassword();check = 1;}
		    else if(choice.equals("/2")) {viewAllRequests();}
		    else if(choice.equals("/3")) {viewPendingRequests();}
		    else if(choice.equals("/4")) {viewAllProjects();}
		    else if(choice.equals("/5")) {createProject();}
		    else if(choice.equals("/6")) {changeTitle();}
		    else if(choice.equals("/7")) {settleRequests();}
		    else if(choice.equals("/8")) {transfer();}
		    else if(choice.equals("/0")) {System.out.println("Logging out...");}

		    else {System.out.println("Invalid option");}
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
	/**
	 * Remvoe a project from list of projects
	 * @param projectID index of project in local database
	 */
	public void removeProjectFromArray(int projectID){
		projectArray.remove(Integer.valueOf(projectID));
	}

	/**
	 * Add a project to list of projects
	 * @param projectID index of project in local database
	 */
	public void addProjectToArray(int projectID){
		projectArray.add(projectID);	
	}
	public void addToOutgoingRequest(int index) {
		outgoingRequest.add(index);
	}
	
}