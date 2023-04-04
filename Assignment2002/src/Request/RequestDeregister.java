package Request;

import Test.Project;
import Test.Student;
import Test.Supervisor;
import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectStatusUpdator;
import databaseUser.databaseUserAccessor;

public class RequestDeregister extends Request{
	public RequestDeregister() {
		this.pending = true;
		this.type = 2;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
		if(approval==true) {
			Project p = DatabaseProjectAccessor.getProject(projectID);	
			Supervisor sp = (Supervisor) databaseUserAccessor.getUser(p.getSupervisorID());
			Student s = (Student)databaseUserAccessor.getUser(senderID);
			s.setDeregister();
			sp.decrementNumProject();
			ProjectStatusUpdator.setAllProjectsAvailable(sp.getUserID());
		}
	}
}