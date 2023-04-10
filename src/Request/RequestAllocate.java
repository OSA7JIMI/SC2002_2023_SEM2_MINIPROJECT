package Request;

import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectStatusUpdator;
import databaseRequest.DatabaseRequestAccessor;
import databaseUser.databaseUserAccessor;
import test.Project;
import test.Student;
import test.Supervisor;

/**
 * Allocation request
 * @author A34_3
 *
 */

public class RequestAllocate extends Request{
	
	/**
	 * Constructor
	 * @param projectID ID of project to be allocated
	 */
	public RequestAllocate(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		setpending(true);
		settype(1);
		setprojectID(projectID);
		p.setStatus(1);
		DatabaseProjectAccessor.updateProjectInDatabase(p);
	}
	
	/**
	 * Second constructor
	 */
	public RequestAllocate() {
	}
	@Override
	public void settleRequest(boolean approval) {
		Student s = (Student)databaseUserAccessor.getUser(this.getsenderID());
		Project p = DatabaseProjectAccessor.getProject(getprojectID());
		setpending(false);
		setapproval(approval);
		
		if(s.getProjectID()!=-1) {
			System.out.println("Student has already been assigned to a project, auto rejecting");
			setapproval(false);
		}
		if(this.getapproval()==false) {
			setprojectID(-1);
			p.setStatus(0);
		}else {
			Supervisor sp = (Supervisor) databaseUserAccessor.getUser(p.getSupervisorID());
			if(sp.getNumProject()>=2) {
				setapproval(false);
				p.setStatus(3);
				System.out.println("Supervisor limit reached, this request is automatically rejected.");
			}else {
				//Get student from userArray, then use the .setProject()
				//method to allocate project to student
				s.setProject(this.getprojectID());
				p.setStudent(getsenderID());
				sp.incrementNumProject();
				p.setStatus(2);
				if(sp.getNumProject()==2) {
					//call method to make all projects under supervisor unavailable
					ProjectStatusUpdator.setAllProjectsUnavailable(sp.getUserID());
				}
			}
		}
		DatabaseRequestAccessor.updateRequestInArray(this, getrequestIndex());
	}
}