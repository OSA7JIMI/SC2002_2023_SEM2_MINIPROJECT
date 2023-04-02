package databaseRequest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Request.Request;
import Test.Project;
import Test.Supervisor;
import Test.User;
import databaseUser.databaseUserAccessor;

public class RequestReader {

	private static final String COMMA_DELIMITER = ",";

	public static void main(String[] args) {
		RequestReader database = new RequestReader();
		database.init_database();
		
		//DEBUG
		System.out.println("request array size is "+DatabaseRequestAccessor.getSize());
		//DEBUG
		
	}

	
	public  void init_database() {
		String Project_path = "C:/Users/Dreamcore/OneDrive/Desktop/2002CSV/Frequest_list.txt";
		read_csv_to_records(Project_path);
	}
	
	public void read_csv_to_records(String file_path) {
		try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
		    String line;
		    //br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        if(values.length!=0) {		
		        	
		        	int ID = Integer.parseInt(values[0]);
		        	int type = Integer.parseInt(values[1]);
		        	String senderID = values[2];
		        	String receiverID = values[3];
		        	boolean pending = false;
		        	if(values[4].equals("1")) pending = true;
		        	boolean approval = false;
		        	if(values[5].equals("1")) approval = true;
		        	String title = null;
		        	if(values[6]!=null) title = values[5];
		        	int projectID = -1;
		        	if(!values[7].equals("-1")) projectID = Integer.parseInt(values[7]);
		        	String replacementID = null;
		        	if(!values[8].equals("null")) replacementID = values[8];
		        	
		        	Request r = new Request();
		        	r.requestIndex = ID;
		        	r.type = type;
		        	r.senderID = senderID;
		        	r.receiverID = receiverID;
		        	r.pending = pending;
		        	r.approval = approval;
		        	r.Title = title;
		        	r.projectID = projectID;
		        	r.replacementID = replacementID;
		        	DatabaseRequestAccessor.addRequest(r);
		        	
		        	User sender = databaseUserAccessor.getUser(senderID);
		        	User receiver = databaseUserAccessor.getUser(receiverID);
		        	sender.addToIncomingRequest(ID);
		        	receiver.addToOutgoingRequest(ID);        	
		        }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
