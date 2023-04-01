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
	
	
	
	public void setDeregister() {
		deregistered = true;
	}
	
	public void viewAllProjects() {
		ProjectPrinter.printAllAvailable(); //implement later w method chain
	}
	
	
	public void viewProject() {		
		Project p = DatabaseProjectAccessor.getProject(projectID);
		Supervisor sp = (Supervisor) databaseUserAccessor.getUser(supervisorID);
		System.out.println("Your project title is: " + p.getTitle());
		System.out.println("Your supervisor is: " + sp.getName());
	}
	
	public void changeTitle() {
		System.out.println("Enter new title");
		String newTitle = sc.nextLine();
		Request r = new RequestChangeTitle(newTitle);
		r.senderID = this.getUserID();
		r.receiverID = supervisorID;
		int index = DatabaseRequestAccessor.addRequest(r);
		r.requestIndex = index;
		this.outgoingRequest.add(index);
		Supervisor sp = (Supervisor) databaseUserAccessor.getUser(supervisorID);
		sp.addToIncomingRequest(index);
	}
	
	public void allocate() {
		if(!deregistered) {
			System.out.println("Enter projectID to be allocated");
			int projectID = sc.nextInt();
			Request r = new RequestAllocate(projectID);
			r.senderID = this.getUserID();
			r.receiverID = DatabaseProjectAccessor.getProject(projectID).getSupervisor().getUserID();
			int index = DatabaseRequestAccessor.addRequest(r);
			Supervisor s = DatabaseProjectAccessor.getProject(projectID).getSupervisor();
			this.incomingRequest.add(index);
			s.addToIncomingRequest(index);
		}
		else {
			System.out.println("Since you have previously deregistered a project, you are not allowed to register for another one.");
		}
		
	}
	
	public void deregister() {
		System.out.println("Deregistering project request sent");
		Request r = new RequestDeregister();
		r.senderID = this.getUserID();
		r.receiverID = FYPcoor.getUserID();
		int index = DatabaseRequestAccessor.addRequest(r);
		outgoingRequest.add(index);
		User.FYPcoor.addToIncomingRequest(index);
	}
	
	public void displayOptions() {
		System.out.println("Welcome" + super.getName());
		if (projectID==-1) {
			System.out.println("/1: View all available projects");
			System.out.println("/2: Request project allocation");
			System.out.println("/3: Change your password");
			String choice = sc.nextLine();
			if(choice.equals("/1")) {
				ProjectPrinter.printAllAvailable();
			}else if(choice.equals("/2")) {
				allocate();
			}else if(choice.equals("/3")) {
				setPassword();
			}else if(choice.equals("/4")) {
				System.out.println("Invalid option");
			}
		}
			
		else {
			System.out.println("/1: View your project");
			System.out.println("/2: Request to change your project title");
			System.out.println("/3: Request project deregistration");
			System.out.println("/4: Change your password");
			String choice = sc.nextLine();
			if(choice.equals("/1")) {
				ProjectPrinter.printAllAvailable();
			}else if(choice.equals("/2")) {
				changeTitle();
			}else if(choice.equals("/3")) {
				deregister();
			}else if(choice.equals("/4")) {
				setPassword();
			}else {
				System.out.println("Invalid option");
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
			for(int i=0; i<incomingRequest.size(); i++) {
				RequestPrinter.printAllRequest(incomingRequest.get(i));
			}
			for(int i=0; i<outgoingRequest.size(); i++) {
				RequestPrinter.printAllRequest(outgoingRequest.get(i));
			}
		
		}
}