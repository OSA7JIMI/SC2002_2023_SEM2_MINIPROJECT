package Test;
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

public class Student extends User {
	private int projectID = -1;
	private String supervisorID;
	private boolean deregistered = false;
	
	private ArrayList<Integer> incomingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> outgoingRequest = new ArrayList<Integer>();
	
	Scanner sc = new Scanner(System.in);
	
	public Student(String name, String email, String ID) {
		super(name, email, ID); 
	}
	
	public void setSupervisor(String sp) {
		this.supervisorID = sp;
	}
	
	public void setProject(int projectID) {
		this.projectID = projectID;
	}
	
	public int getProjectID() {
		return this.projectID;
	}
	
	public void setDeregister() {
		deregistered = true;
	}
	
	public void viewAllProjects() {
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
		r.senderID = this.getUserID();
		r.receiverID = supervisorID;
		r.requestIndex = DatabaseRequestAccessor.getSize();
		DatabaseRequestAccessor.addRequest(r);
		this.outgoingRequest.add(r.requestIndex);
		Supervisor sp = (Supervisor) databaseUserAccessor.getUser(supervisorID);
		sp.addToIncomingRequest(r.requestIndex);
	}
	
	public void allocate() {
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
			r.requestIndex = DatabaseRequestAccessor.getSize();
			r.senderID = this.getUserID();
			r.receiverID = FYPcoor.getUserID();
			int index = DatabaseRequestAccessor.addRequest(r);
			this.incomingRequest.add(index);
			FYPcoor.addToIncomingRequest(index);
		}
		else {
			System.out.println("Since you have previously deregistered a project, you are not allowed to register for another one.");
		}
		
	}
	
	private void deregister() {
		System.out.println("Deregistering project request sent");
		Request r = new RequestDeregister();
		r.senderID = this.getUserID();
		r.receiverID = FYPcoor.getUserID();
		int index = DatabaseRequestAccessor.addRequest(r);
		outgoingRequest.add(index);
		User.FYPcoor.addToIncomingRequest(index);
	}
	
	public void displayOptions() { //Student
	    String choice = "dummy";
	    
	    while (!choice.equals("/0")) {
	      System.out.println("/0: Log out");
	      System.out.println("/1: Change password");
	      System.out.println("/2: View request history");
	      System.out.println("/3: View pending requests");
	      if (projectID==-1) {
	        System.out.println("/4: View all available projects");
	        System.out.println("/5: Request project allocation");
	        choice = sc.nextLine();
	        if (choice.equals("/1")) {setPassword();}
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
	        if (choice.equals("/1")) {setPassword();}
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

	public void viewPendingRequests(){
		System.out.println("---Pending requests---");
		for(int i=0; i<incomingRequest.size(); i++) {
			RequestPrinter.printAllPendingRequest(incomingRequest.get(i));
		}
		for(int i=0; i<outgoingRequest.size(); i++) {
			RequestPrinter.printAllPendingRequest(outgoingRequest.get(i));
		}
	}

	public void viewAllRequests() {
		System.out.println("---All requests---");
		System.out.println("Incoming");
		for(int i=0; i<incomingRequest.size(); i++) {
			RequestPrinter.printAllRequest(incomingRequest.get(i));
		}
		System.out.println(" ");
		System.out.println("Outgoing");
		for(int i=0; i<outgoingRequest.size(); i++) {
			RequestPrinter.printAllRequest(outgoingRequest.get(i));
		}
		
	}
						  
	public void addToIncomingRequest(int index) {
		incomingRequest.add(index);
	}
	public void addToOutgoingRequest(int index) {
		outgoingRequest.add(index);
	}
						  
}