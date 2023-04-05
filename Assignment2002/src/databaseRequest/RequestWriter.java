package databaseRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Request.Request;
import Test.Project;

public class RequestWriter {

	public static void run() {
		try {
			
			PrintWriter out = new PrintWriter(new FileWriter("C:/Users/user/Desktop/2002CSV/Frequest_list.txt", false));
			for(int i=0; i<DatabaseRequestAccessor.getSize(); i++) {
				Request r = DatabaseRequestAccessor.getRequest(i);
				int ID = r.requestIndex;
				int type = r.type;
				String senderID = r.senderID;
				String receiverID = r.receiverID;
				int pending = 0;
				if(r.pending == true) pending = 1;
				int approval = 0;
				if(r.approval == true) approval = 1;
				String title = "null";
				if(r.Title!=null) title = r.Title;
				int projectID = -1;
				if(r.projectID!=-1) projectID = r.projectID;
				String replacementID = "null";
				if(r.replacementID!=null) replacementID = r.replacementID;
				
				out.printf("%d,%d,%s,%s,%d,%d,%s,%d,%s\n", ID, type, senderID, receiverID, pending, approval, title, projectID, replacementID);
			}
			
			out.close();
			
		} catch (IOException e) {
			
			System.out.println("File not found");
			e.printStackTrace();
			
		} finally {
			
			System.out.println("Request updated");
		}
	}

}