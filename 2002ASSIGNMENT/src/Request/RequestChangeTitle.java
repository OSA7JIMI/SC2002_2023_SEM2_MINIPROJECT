package Request;

import databaseProject.DatabaseProjectAccessor;
import databaseRequest.DatabaseRequestAccessor;
import Test.Project;

public class RequestChangeTitle extends Request{
	public RequestChangeTitle(String newTitle) {
		this.pending = true;
		this.type = 0;
		this.Title = newTitle;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
		if(approval==true) {
			Project p = DatabaseProjectAccessor.getProject(projectID);
			p.setTitle(Title);
			DatabaseProjectAccessor.updateProjectInDatabase(p);
		}
		DatabaseRequestAccessor.updateRequestInArray(this, requestIndex);
	}
}