package request;

public class Request {
	public String senderID;
	public String receiverID;
	public boolean pending;
	public boolean approval;
	//0 for changeTitle, 1 for Allocate, 2 for De-register, 3 for Transfer
	public int type;
	public String Title;
	public String projectID;
	
	public void settleRequest() {
		return;
	}
}
