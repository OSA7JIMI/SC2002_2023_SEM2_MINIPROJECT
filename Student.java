package sc2002_assignment;
import java.util.ArrayList;
import java.util.Scanner;

import databaseProject.DatabaseProjectAccessor;
import request.Request;
import request.RequestAllocate;
import request.RequestChangeTitle;
import request.RequestDeregister;

public class Student extends User {
	private Project p;
	private Supervisor s;
	private Supervisor FYPcoor;
	private boolean deregistered = false;
	
	private ArrayList<Integer> incomingRequest = new ArrayList<Integer>();
	private ArrayList<Integer> outgoingRequest = new ArrayList<Integer>();
	
	Scanner sc = new Scanner(System.in);
	
	Student(String name, String email, String ID) {
		super(name, email, ID); 
	}
	
	public void setProject(Project p) {
		this.p = p;
	}
	
	public void setFYPcoor(Supervisor FYPcoor) {
		if(!deregistered) {
			this.FYPcoor = FYPcoor;
		}
		
	}
	
	public void viewAllProject() {
		DatabaseProjectAccessor.viewAllProject();
	}
	
	
	public void viewProject() {
		
		if (p == null) {
			System.out.println("You have not selected a project.");
		}
		else {
			System.out.println("Your project title is: " + p);
		}
	}
	
	public void changeTitle() {
		System.out.println("Enter new title");
		String newTitle = sc.next();
		Request r = new RequestChangeTitle(newTitle);
		r.senderID = this.getUserID();
		r.receiverID = s.getUserID();
		int index = DatabaseRequestAccessor.addRequest(r);
		r.requestIndex = index;
		this.outgoingRequest.add(index);
		s.addToIncomingRequest(index);
	}
	
	public void allocate() {
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
	
	public void deregister() {
		System.out.println("Deregistering project request sent");
		Request r = new RequestDeregister();
		r.senderID = this.getUserID();
		r.receiverID = FYPcoor.getUserID();
		int index = DatabaseRequestAccessor.addRequest(r);
		outgoingRequest.add(index);
		FYPcoor.addToIncomingRequest(index);
		deregistered = true;

	}
	
	
	public void viewAllOutgoingRequests() {
		for(int i=0; i<outgoingRequest.size(); i++) {
			Request r = DatabaseRequestAccessor.getRequest(this.outgoingRequest.get(i));
			System.out.println("SenderID: "+r.senderID);
			System.out.println("ReceiverID: "+r.receiverID);
			System.out.println("Type: "+r.type);
			System.out.println("Approval: "+r.approval);
			System.out.println("Pending: "+r.pending);
		}
	}
	
	
}