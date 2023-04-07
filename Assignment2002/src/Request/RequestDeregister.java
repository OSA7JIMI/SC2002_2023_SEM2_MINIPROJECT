package Request;

import Test.Project;
import Test.Student;
import Test.Supervisor;
import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectStatusUpdator;
import databaseUser.databaseUserAccessor;

public class RequestDeregister extends Request{
	public RequestDeregister() {
		setpending(true);
		settype(2);
	}
	@Override
	public void settleRequest(boolean approval) {
		setpending(false);
		setapproval(approval);
		if(approval==true) {
			Project p = DatabaseProjectAccessor.getProject(getprojectID());	
			Supervisor sp = (Supervisor) databaseUserAccessor.getUser(p.getSupervisorID());
			Student s = (Student)databaseUserAccessor.getUser(getsenderID());
			s.setDeregister();
			sp.decrementNumProject();
			ProjectStatusUpdator.setAllProjectsAvailable(sp.getUserID());
		}
	}
}
