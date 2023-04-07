package Request;

import databaseProject.DatabaseProjectAccessor;
import databaseRequest.DatabaseRequestAccessor;
import Test.Project;

public class RequestChangeTitle extends Request{
	public RequestChangeTitle(String newTitle) {
		setpending(true);
		settype(0);
		setTitle(newTitle);
	}
	public RequestChangeTitle() {
	}
	@Override
	public void settleRequest(boolean approval) {
		setpending(false);
		setapproval(approval);
		if(getapproval()==true) {
			Project p = DatabaseProjectAccessor.getProject(getprojectID());
			p.setTitle(getTitle());
			DatabaseProjectAccessor.updateProjectInDatabase(p);
		}
		DatabaseRequestAccessor.updateRequestInArray(this, getrequestIndex());
	}
}
