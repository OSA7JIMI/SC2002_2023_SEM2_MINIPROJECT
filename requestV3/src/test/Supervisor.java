package test;
import java.util.ArrayList;
import java.util.Scanner;

import databaseProject.DatabaseProjectAccessor;
import request.Request;

public class Supervisor extends User{
	private ArrayList<Integer> projectArray = new ArrayList<Integer>();
	private int numProject;
	private int numProjectCreated = 0;
	private boolean isFYP = false;
	private Supervisor FYPcoor;
	private ArrayList<Integer> incomingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> outgoingRequest = new ArrayList<Integer>();
	
	Scanner sc = new Scanner(System.in);
	
	Supervisor(String name, String email, String ID) {
		super(ID);
		this.setName(name);
		this.setEmail(email);
	}
	
	//NEW
	public void setFYP() {
		isFYP = true;
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
	public void viewAllProject() {	
		int i;
		for (i = 0; i < projectArray.size(); i ++) {
			System.out.println("Project ID: " + DatabaseProjectAccessor.getProject(projectArray.get(i)).getID() + "  Title: " + DatabaseProjectAccessor.getProject(projectArray.get(i)).getTitle() + "  Status: " + DatabaseProjectAccessor.getProject(projectArray.get(i)).getStatus());
		}
	}
	
	/*public void changeTitle() {
		int i;
		System.out.println("You got these projects: ");
		for(i = 0; i < projectArray.length; i++) {
			System.out.print("ProjectID: " + projectArray[i].projectID);
			System.out.print("Project Title: " + projectArray[i].projectTitle);
			System.out.print("Project Status" + projectArray[i].status);
			System.out.println("Student: " + projectArray[i].s);
		}
		System.out.println("Please enter the project ID you want to change title: ");
		Scanner sc = new Scanner (System.in);
		int projectId = sc.nextInt();
		System.out.println("Please enter the new title: ");
		String newTitle = sc.nextLine();
		for(i = 0; i < projectArray.length; i++) {
			if(projectArray[i].projectID == projectId]) {
				projectArray[i].projectTitle = newTitle;
			}
		}
		System.out.println("Down! The new projects list is: ");
		for(i = 0; i < projectArray.length; i++) {
			System.out.print("ProjectID: " + projectArray[i].projectID);
			System.out.print("Project Title: " + projectArray[i].projectTitle);
			System.out.print("Project Status" + projectArray[i].status);
			System.out.println("Student: " + projectArray[i].s);
		}
	}*/
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
	
	
}
