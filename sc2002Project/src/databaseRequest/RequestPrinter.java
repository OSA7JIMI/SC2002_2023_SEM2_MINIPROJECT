package databaseRequest;

import Request.Request;
import Request.RequestTransfer;

public class RequestPrinter {
	public static void printAllRequest(int requestID) {
		Request r = DatabaseRequestAccessor.getRequest(requestID);
		System.out.println("-----------");
		System.out.println("Request ID: "+r.getrequestIndex());
		System.out.println("SenderID: "+r.getsenderID());
		System.out.println("Type : "+r.gettype());
	    String status; 
	    if (r.getpending()) status = "Pending";
	    else if (r.getapproval()) status = "Approved";
	    else status = "Rejected";
	    System.out.println("Status: "+ status);
		switch(r.gettype()) {
		case 0:
			System.out.println("Title : "+r.getTitle());
			break;
		case 1:
			System.out.println("ProjectID : "+r.getprojectID());
			break;
		case 2:
			System.out.println("Deregistering");
			break;
		case 3:
			RequestTransfer r1 = (RequestTransfer) r;
			System.out.println("ReplacementID : "+r1.getreplacementID());
			System.out.println("ProjectID : "+r1.getprojectID());
			break;
		}
	}
	
	public static void printAllPendingRequest(int requestID) {
		Request r = DatabaseRequestAccessor.getRequest(requestID);
		if(r.getpending()==true) printAllRequest(requestID);
	}
	public static void alertMessage(){ //added this
		System.out.println("The replacement supervisor already supervises 2 projects.");
		System.out.println("Enter 0 to reject, 1 to approve again");
	}
}