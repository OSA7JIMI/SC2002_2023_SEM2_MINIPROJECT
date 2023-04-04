package Request;

import Test.Project;

import Test.User;

public abstract class Request {
	
	public int requestIndex;
	public String senderID;
	public String receiverID;
	public boolean pending;
	public boolean approval;
	public boolean dblapproval = true; // added this
	//0 for changeTitle, 1 for Allocate, 2 for De-register, 3 for Transfer
	public int type;
	//contains the index position in the sender's outgoing request array
	public String Title;
	public int projectID = -1;
	public String replacementID;
	
	public abstract void settleRequest(boolean approval);
}