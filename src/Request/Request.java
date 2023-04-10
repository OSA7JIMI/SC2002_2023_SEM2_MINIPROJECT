package Request;



/**
 * 
 * @author A34_3 
 *
 */
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
	
	/**
	 * Settles a request
	 * @param approval Approval status
	 */
	public abstract void settleRequest(boolean approval);

	/**
	 * Setter
	 * @param index index of request
	 */
	public void setrequestIndex(int index) {
		requestIndex = index;
	}
	
	/**
	 * Getter
	 * @return index of request
	 */
	public int getrequestIndex() {
		return requestIndex;
	}

	/**
	 * Setter
	 * @param sendID ID of sender
	 */
	public void setsenderID(String sendID) {
		senderID = sendID;
	}
	
	/**
	 * Getter
	 * @return ID of sender
	 */
	public String getsenderID() {
		return senderID;
	}

	/**
	 * Setter
	 * @param receiveID ID of receiver
	 */
	public void setreceiverID(String receiveID) {
		receiverID = receiveID;
	}
	
	/**
	 * Getter
	 * @return ID of receiver
	 */
	public String getreceiverID() {
		return receiverID;
	}

	/**
	 * Setter
	 * @param pending_ Pending status
	 */
	public void setpending(boolean pending_) {
		pending = pending_;
	}
	
	/**
	 * Getter
	 * @return pending status
	 */
	public boolean getpending() {
		return pending;
	}

	/**
	 * Setter
	 * @param approval_ Approval status
	 */
	public void setapproval(boolean approval_) {
		approval = approval_;
	}
	
	/**
	 * Getter
	 * @return Approval status
	 */
	public boolean getapproval() {
		return approval;
	}

	/**
	 * Setter
	 * @param dblapproval_ Approval status after checking alert message
	 */
	public void setdblapproval(boolean dblapproval_) {
		dblapproval = dblapproval_;
	}
	
	/**
	 * Getter
	 * @return Approval status after checking alert message
	 */
	public boolean getdblapproval() {
		return dblapproval;
	}

	/**
	 * Setter
	 * @param typ Type of request
	 */ 
	public void settype(int typ) {
		type = typ;
	}
	
	/**
	 * Getter
	 * @return Type of request
	 */
	public int gettype() {
		return type;
	}

	/**
	 * Setter
	 * @param title Suggested new title of project
	 */
	public void setTitle(String title) {
		Title = title;
	}
	
	/**
	 * Getter
	 * @return Suggested new title of project
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * Setter
	 * @param projID ID of project
	 */
	public void setprojectID(int projID) {
		projectID = projID;
	}
	
	/**
	 * Getter
	 * @return ID of project
	 */
	public int getprojectID() {
		return projectID;
	}

	/**
	 * Setter
	 * @param replaceID ID of replacement supervisor
	 */
	public void setreplacementID(String replaceID) {
		replacementID = replaceID;
	}
	
	/**
	 * Getter
	 * @return ID of replacement supervisor
	 */
	public String getreplacementID() {
		return replacementID;
	}
}