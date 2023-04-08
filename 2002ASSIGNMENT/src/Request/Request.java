package Request;

import test.Project;

import test.User;

public abstract class Request {
	
	private int requestIndex;
	private String senderID;
	private String receiverID;
	private boolean pending;
	private boolean approval;
	private boolean dblapproval = true; // added this
	//0 for changeTitle, 1 for Allocate, 2 for De-register, 3 for Transfer
	private int type;
	//contains the index position in the sender's outgoing request array
	private String Title;
	private int projectID = -1;
	private String replacementID;
	
	public abstract void settleRequest(boolean approval);

	public void setrequestIndex(int index) {
		requestIndex = index;
	}
	public int getrequestIndex() {
		return requestIndex;
	}

	public void setsenderID(String sendID) {
		senderID = sendID;
	}
	public String getsenderID() {
		return senderID;
	}

	public void setreceiverID(String receiveID) {
		receiverID = receiveID;
	}
	public String getreceiverID() {
		return receiverID;
	}

	public void setpending(boolean pending_) {
		pending = pending_;
	}
	public boolean getpending() {
		return pending;
	}

	public void setapproval(boolean approval_) {
		approval = approval_;
	}
	public boolean getapproval() {
		return approval;
	}

	public void setdblapproval(boolean dblapproval_) {
		dblapproval = dblapproval_;
	}
	public boolean getdblapproval() {
		return dblapproval;
	}

	public void settype(int typ) {
		type = typ;
	}
	public int gettype() {
		return type;
	}

	public void setTitle(String title) {
		Title = title;
	}
	public String getTitle() {
		return Title;
	}

	public void setprojectID(int projID) {
		projectID = projID;
	}
	public int getprojectID() {
		return projectID;
	}

	public void setreplacementID(String replaceID) {
		replacementID = replaceID;
	}
	public String getreplacementID() {
		return replacementID;
	}
}