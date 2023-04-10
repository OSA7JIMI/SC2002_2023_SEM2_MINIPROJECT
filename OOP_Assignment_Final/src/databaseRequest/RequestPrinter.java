package databaseRequest;

import Request.Request;
import Request.RequestTransfer;

/**
 * Methods for printing request information and alert messages based on various criteria
 * @author A34_3
 * @version 9 Apr 2023
 */
public class RequestPrinter {
	
	/**
	 * Prints details of a particular request
	 * @param requestID ID of request
	 */
	public static void printRequest(int requestID) {
		Request r = DatabaseRequestAccessor.getRequest(requestID);		
		System.out.println("-----------");
		System.out.println("Request ID: "+r.getrequestIndex());
		System.out.println("SenderID: "+r.getsenderID());
		System.out.println("ReceiverID: "+r.getreceiverID());
		System.out.print("Type: ");
		switch(r.gettype()) {
		case 0:
			System.out.print("Change Title");
			break;
		case 1:
			System.out.print("Allocate Project");
			break;
		case 2:
			System.out.print("Deregister");
			break;
		case 3:
			System.out.print("Transfer");
			break;
		}
	
		System.out.println(" ");
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

	/**
	 * Prints pending requests
	 * @param requestID ID of request
	 */
	public static void printAllPendingRequest(int requestID) {
		Request r = DatabaseRequestAccessor.getRequest(requestID);
		if(r.getpending()==true) printRequest(requestID);
	}
	
	/**
	 * Prints an alert message when the requested replacement supervisor has reached the supervision limit
	 */
	public static void alertMessage(){ //added this
		System.out.println("The replacement supervisor already supervises 2 projects.");
		System.out.println("Enter 0 to reject, 1 to approve again");
	}
}