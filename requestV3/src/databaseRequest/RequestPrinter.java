package databaseRequest;

import Request.Request;
import Request.RequestTransfer;

public class RequestPrinter {
	public static void printAllRequest(int requestID) {
		Request r = DatabaseRequestAccessor.getRequest(requestID);
		System.out.println("-----------");
		System.out.println("Request ID: "+r.requestIndex);
		System.out.println("Pending: "+r.pending);
		System.out.println("Type : "+r.type);
		switch(r.type) {
		case 0:
			System.out.println("Title : "+r.Title);
			break;
		case 1:
			System.out.println("ProjectID : "+r.projectID);
			break;
		case 2:
			RequestTransfer r1 = (RequestTransfer) r;
			System.out.println("ReplacementID : "+r1.replacementID);
			System.out.println("ProjectID : "+r1.projectID);
		}
	}
}
