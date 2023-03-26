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
	public String getType() {
		return "Change Title";
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
		if(approval==true) {
			Project p = DatabaseProjectAccessor.getProject(projectID);
			p.setTitle(Title);
			DatabaseProjectAccessor.updateProjectInDatabase(p);
			//Get user from userArray, then update their project with the
			//setProject() method
		}
		DatabaseRequestAccessor.updateRequestInArray(this, requestIndex);
	}
}
