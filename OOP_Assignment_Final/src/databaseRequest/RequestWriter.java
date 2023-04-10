package databaseRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Request.Request;
/**
 * Updates the external record of Requests
 * @author A34_3
 * @version 9 Apr 2023
 */
public class RequestWriter {
	/**
	 * Iterates through the local array and writes to the csv
	 */
	public static void run() {
		try {
			
			PrintWriter out = new PrintWriter(new FileWriter("databases/Frequest_list.txt", false));
			for(int i=0; i<DatabaseRequestAccessor.getSize(); i++) {
				Request r = DatabaseRequestAccessor.getRequest(i);
				int ID = r.getrequestIndex();
				int type = r.gettype();
				String senderID = r.getsenderID();
				String receiverID = r.getreceiverID();
				int pending = 0;
				if(r.getpending() == true) pending = 1;
				int approval = 0;
				if(r.getapproval() == true) approval = 1;
				String title = "null";
				if(r.getTitle()!=null) title = r.getTitle();
				int projectID = -1;
				if(r.getprojectID()!=-1) projectID = r.getprojectID();
				String replacementID = "null";
				if(r.getreplacementID()!=null) replacementID = r.getreplacementID();
				
				out.printf("%d,%d,%s,%s,%d,%d,%s,%d,%s\n", ID, type, senderID, receiverID, pending, approval, title, projectID, replacementID);
			}
			
			out.close();
			
		} catch (IOException e) {
			
			System.out.println("File not found");
			e.printStackTrace();
			
		} finally {
			
			System.out.println("Request CSV record has been updated");
		}
	}

}