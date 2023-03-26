package Request;

import Test.Project;
import Test.Supervisor;
import databaseProject.DatabaseProjectAccessor;

public class RequestTransfer extends Request{
	int replacementID;
	int projectIDtoTransfer;
	public RequestTransfer(int projectID, int supervisorID) {
		this.pending = true;
		this.type = 3;
		this.replacementID = supervisorID;
		this.projectIDtoTransfer = projectID;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
		if(approval==true) {
			Project p = DatabaseProjectAccessor.getProject(projectIDtoTransfer);
			p.setSupervisor(replacementID);
			DatabaseProjectAccessor.updateProjectInDatabase(p);
		}
	}
}