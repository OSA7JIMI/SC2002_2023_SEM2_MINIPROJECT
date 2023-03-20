package request;

import test.User;

public class Request {
	
	public User sender;
	
	public String senderID;
	public String receiverID;
	public boolean pending;
	public boolean approval;
	//0 for changeTitle, 1 for Allocate, 2 for De-register, 3 for Transfer
	public int type;
	//contains the index position in the sender's outgoing request array
	public int senderIndex;
	public String Title;
	public String projectID;
	
	public void settleRequest(boolean approval) {
		return;
	}
}