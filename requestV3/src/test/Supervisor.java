package test;

import java.util.ArrayList;
import java.util.Scanner;

import databaseProject.DatabaseProjectAccessor;
import request.*;
import databaseRequest.DatabaseRequestAccessor;
import databaseRequest.RequestPrinter;

public class Supervisor extends User{
	private int numProject = 0;
	private FYPcoordinator FYPcoor = null;
	private ArrayList<Integer> incomingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> outgoingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> projectArray = new ArrayList<Integer>();
	
	Scanner sc = new Scanner(System.in);
	
	Supervisor(String name, String email, String ID) {
		super(name, email, ID);
	}
	
	public void setFYPcoor(FYP coordinator FYPcoor){
		this.FYPcoor = FYPcoor;
	}
	
	public void incrementNumProject {
		numProject++;
	}
	
	public ArrayList<Integer> getProjectArray {
		return projectArray;
	}
	
	public int getNumProject {
		return numProject;
	}
		
	//NEW
	public void addToIncomingRequest(int index) {
		incomingRequest.add(index);
	}
	
	//NEW
	public void settleIncomingRequest(int index) {
		Request r = DatabaseRequestAccessor.getRequest(index);
		System.out.println("Enter 0 to reject, 1 to approve");
		int choice = sc.nextInt();
		if(choice==0) {
			r.settleRequest(false);
		}else {
			r.settleRequest(true);
		}
		
	}
	
	//NEW
	public void createProject() {
		System.out.println("Enter project title");
		String title = sc.next();
		Project p = new Project(DatabaseProjectAccessor.getSize(), title, 0, this);
		DatabaseProjectAccessor.addProject(p);
		projectArray.add(p);
		numProjectCreated++;
		
	}
	public void viewAllProjects() {	
		int i;
		for (i = 0; i < projectArray.size(); i ++) {
			System.out.println("Project ID: " + DatabaseProjectAccessor.getProject(projectArray.get(i)).getID() + "  Title: " + DatabaseProjectAccessor.getProject(projectArray.get(i)).getTitle() + "  Status: " + DatabaseProjectAccessor.getProject(projectArray.get(i)).getStatus());
		}
	}

	public void  transfer() {
		System.out.println("Please enter the projectID you want to transfer to another supervisor: ");
		int projectID = sc.nextInt();
		System.out.println("Please enter the replacement supervisorId");
		int replacementID = sc.nextInt();
		Request r = new RequestTransfer(projectID, replacementID);
		int index = DatabaseRequestAccessor.addRequest(r);
		this.outgoingRequest.add(index);
		//will change the line below after userarray is implemented
		FYPcoor.addToIncomingRequest(index);
	}
	
	public void viewAllRequests() {
		System.out.println("---Viewing all requests---");
		for(int i=0; i<incomingRequest.size(); i++) {
			RequestPrinter.printAllRequest(DatabaseRequestAccessor.getRequest(outgoingRequest.get(i)).requestID);
		}
		for(int i=0; i<outgoingRequest.size(); i++) {
			RequestPrinter.printAllRequest(DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).requestID);
		}
		
	}
	public void displayOptions() {
		System.out.println("/1: view all requests");
		System.out.println("/2: view all projects");
		System.out.println("/3: create a new project");
		System.out.println("/4: settle requests");
		System.out.println("/5: view request history");
		System.out.println("/6: request project transfer");

		String choice = sc.nextLine();
		if(choice.equals("/1")) {
			this.viewAllRequests();
		}
		else if(choice.equals("/2")) {
			this.viewAllProjects();
		}
		else if(choice.equals("/3")) {
			this.createProject();
		}
		else if(choice.equals("/4")) {
			if(incomingRequest.size() == 0) {
				System.out.println("There's no incoming request!");

			}
			else{
				System.out.println("Enter the student ID to settle his/her request");
				String stuID = sc.next();
				int i, ID = 0;
				for(i = 0; i < incomingRequest.size();i++) {
					if(DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).senderID == stuID) {
						ID = incomingRequest.get(i);
						break;
				    }
			    }
				if(ID == 0) {
					System.out.println("This student didn't make request to you");
				}
				else this.settleIncomingRequest(ID);
			}
		}
		//NEW
		else if(choice.equals("/5")) {
			this.viewRequestHistory();
		}
		else if(choice.equals("/6")) {
			this.transfer();
		}
		else {
			System.out.println("Invalid option chosen. Please try again.");
		}
	}
}
				
	
	Public void viewPendingRequests(){
		System.out.println("---Pending requests---");
		for(int i=0; i<incomingRequest.size(); i++) {
			RequestPrinter.printAllPendingRequest(DatabaseRequestAccessor.getRequest(outgoingRequest.get(i)).requestID);
		}
		for(int i=0; i<outgoingRequest.size(); i++) {
			RequestPrinter.printAllPendingRequest(DatabaseRequestAccessor.getRequest(incomingRequest.get(i)).requestID);
		}
	}
	
	
}
