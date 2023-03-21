package test;
import java.util.ArrayList;
import java.util.Scanner;

import databaseProject.DatabaseProjectAccessor;
import request.Request;
import request.RequestAllocate;
import request.RequestChangeTitle;
import request.RequestDeregister;

public class Student extends User {
	private Project p;
	private ArrayList<Request> outgoingRequest = new ArrayList<Request>();
	private int outgoingRequestIndex = 0;
	private Supervisor s;
	private Supervisor FYPcoor;
	
	Scanner sc = new Scanner(System.in);
	
	Student(String name, String email, String ID, Supervisor s) {
		super(ID);
		this.setName(name);
		this.setEmail(email);
		this.s = s;
	}
	
	//NEW
	public void setProject(Project p) {
		this.p = p;
	}
	
	//NEW
	public void setFYPcoor(Supervisor FYPcoor) {
		this.FYPcoor = FYPcoor;
	}
	
	//NEW
	public void viewAllProject() {
		DatabaseProjectAccessor.viewAllProject();
	}
	
	
	public void viewProject() {
//		edit based on project class
		
		if (p == null) {
			System.out.println("You have not selected a project.");
		}
		else {
			System.out.println("Your project title is: " + p);
		}
	}
	
	//NEW
	public void changeTitle() {
		System.out.println("Enter new title");
		String newTitle = sc.next();
		Request r = new RequestChangeTitle(newTitle, outgoingRequestIndex);
		r.sender = this;
		r.senderID = this.getUserID();
		r.receiverID = s.getUserID();
		outgoingRequest.add(r);
		s.addToIncomingRequest(r);
		outgoingRequestIndex++;
	}
	
	//NEW
	public void updateOutgoingRequest(int senderIndex, boolean approval) {
		Request r = outgoingRequest.get(senderIndex);
		r.settleRequest(approval);
	}
	
	//NEW
	public void allocate() {
		System.out.println("Enter projectID to be allocated");
		int projectID = sc.nextInt();
		Request r = new RequestAllocate(projectID, outgoingRequestIndex);
		r.sender = this;
		r.senderID = this.getUserID();
		r.receiverID = DatabaseProjectAccessor.getProject(projectID).getSupervisor().getUserID();
		outgoingRequest.add(r);
		Supervisor s = DatabaseProjectAccessor.getProject(projectID).getSupervisor();
		s.addToIncomingRequest(r);
		outgoingRequestIndex++;
	}
	
	//NEW
	public void deregister() {
		System.out.println("Deregistering project request sent");
		Request r = new RequestDeregister(outgoingRequestIndex);
		r.sender = this;
		r.senderID = this.getUserID();
		r.receiverID = s.getUserID();
		outgoingRequest.add(r);
		FYPcoor.addToIncomingRequest(r);
		outgoingRequestIndex++;
	}
	
	
	public void viewAllRequests() {
		for(int i=0; i<outgoingRequest.size(); i++) {
			System.out.println("SenderID: "+outgoingRequest.get(i).senderID);
			System.out.println("ReceiverID: "+outgoingRequest.get(i).receiverID);
			System.out.println("Type: "+outgoingRequest.get(i).type);
			System.out.println("Approval: "+outgoingRequest.get(i).approval);
			System.out.println("Pending: "+outgoingRequest.get(i).pending);
		}
	}
	
	
}