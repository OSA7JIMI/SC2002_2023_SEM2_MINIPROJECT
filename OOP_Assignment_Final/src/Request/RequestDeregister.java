package Request;

import test.Project;
import test.Student;
import test.Supervisor;
import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectStatusUpdator;
import databaseUser.databaseUserAccessor;

/**
 * Deregistration request
 * @author A34_3
 *
 */

public class RequestDeregister extends Request{
	
	/**
	 * Constructor
	 */
	public RequestDeregister() {
		setpending(true);
		settype(2);
		System.out.println("Deregistration request sent");
	}
	@Override
	public void settleRequest(boolean approval) {
		setpending(false);
		setapproval(approval);
		if(approval==true) {
			Project p = DatabaseProjectAccessor.getProject(((Student) databaseUserAccessor.getUser(this.getsenderID())).getProjectID());
			p.setStudent(null);
			p.setStatus(0);
			Supervisor sp = (Supervisor) databaseUserAccessor.getUser(p.getSupervisorID());
			Student s = (Student)databaseUserAccessor.getUser(getsenderID());
			s.setDeregister();
			sp.decrementNumProject();
			ProjectStatusUpdator.setAllProjectsAvailable(sp.getUserID());
		}
	}
}