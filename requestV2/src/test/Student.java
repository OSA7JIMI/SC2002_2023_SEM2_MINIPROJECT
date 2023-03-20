package test;
import java.util.ArrayList;
import java.util.Scanner;
import request.Request;
import request.RequestChangeTitle;

public class Student extends User {
	private String p;
	private ArrayList<Request> outgoingRequest = new ArrayList<Request>();
	private int outgoingRequestIndex = 0;
	private Supervisor s;
	
	Scanner sc = new Scanner(System.in);
	
	Student(String name, String email, String ID, Supervisor s) {
		super(ID);
		this.setName(name);
		this.setEmail(email);
		this.s = s;
	}
	
	
	public void viewAllProject() {
//		edit based on database 
		
		String[] database_array = new String[10];
		database_array[0] = "a";
		for(int i = 0; i<database_array.length; i++) {
			System.out.println(database_array[i]);
		}
		
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
		r.receiverID = this.s.getUserID();
		outgoingRequest.add(r);
		s.addToIncomingRequest(r);
		outgoingRequestIndex++;
	}
	
	//NEW
	public void updateOutgoingRequest(int senderIndex, boolean approval) {
		Request r = outgoingRequest.get(senderIndex);
		r.pending = false;
		r.approval = approval;
	}
	
	public void allocate() {
		Scanner x = new Scanner(System.in);
		System.out.println("Select your project:");
		String projselected = x.nextLine();
	
//		send request
		System.out.println("Registering project request sent");
	}
	
	
	public void deregister() {
		System.out.println("Deregistering project request sent");
		
//		send request
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