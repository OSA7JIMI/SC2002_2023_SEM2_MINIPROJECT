package Request;

import databaseProject.DatabaseProjectAccessor;
import databaseRequest.DatabaseRequestAccessor;
import databaseUser.databaseUserAccessor;
import test.Project;
import test.Student;

/**
 * Change title request
 * @author A34_3
 *
 */
public class RequestChangeTitle extends Request{
	/**
	 * Constructor 
	 * @param newTitle Suggested new title for project
	 */
	public RequestChangeTitle(String newTitle) {
		setpending(true);
		settype(0);
		setTitle(newTitle);
	}
	
	/**
	 * S constructor
	 */
	public RequestChangeTitle() {
	}
	@Override
	public void settleRequest(boolean approval) {
		setpending(false);
		setapproval(approval);
		if(approval==true) {
			Project p = DatabaseProjectAccessor.getProject(((Student) databaseUserAccessor.getUser(this.getsenderID())).getProjectID());
			p.setTitle(getTitle());
			DatabaseProjectAccessor.updateProjectInDatabase(p);
		}
		DatabaseRequestAccessor.updateRequestInArray(this, getrequestIndex());
	}
}