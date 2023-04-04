package Request;

import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectStatusUpdator;
import databaseRequest.DatabaseRequestAccessor;
import databaseRequest.RequestPrinter;
import databaseUser.databaseUserAccessor;
import Test.Project;
import Test.Student;
import Test.Supervisor;

public class RequestAllocate extends Request{
	
	public RequestAllocate(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		this.pending = true;
		this.type = 1;
		this.projectID  = projectID;
		p.setStatus(1);
		DatabaseProjectAccessor.updateProjectInDatabase(p);
	}
	public RequestAllocate() {
	}
	@Override
	public void settleRequest(boolean approval) {
		Student s = (Student)databaseUserAccessor.getUser(this.senderID);
		Project p = DatabaseProjectAccessor.getProject(projectID);
		this.pending = false;
		this.approval = approval;
		
		if(s.getProjectID()!=-1) {
			System.out.println("Student has already been assigned to a project, auto rejecting");
			this.approval = false;
		}
		if(this.approval==false) {
			this.projectID = -1;
			p.setStatus(0);
		}else {
			Supervisor sp = (Supervisor) databaseUserAccessor.getUser(p.getSupervisorID());
			if(sp.getNumProject()>=2) {
				this.approval = false;
				p.setStatus(3);
				System.out.println("Supervisor limit reached");
			}else {
				//Get student from userArray, then use the .setProject()
				//method to allocate project to student
				s.setProject(this.projectID);
				p.setStudent(senderID);
				sp.incrementNumProject();
				p.setStatus(2);
				if(sp.getNumProject()==2) {
					//call method to make all projects under supervisor unavailable
					ProjectStatusUpdator.setAllProjectsUnavailable(sp.getUserID());
				}
			}
		}
		DatabaseRequestAccessor.updateRequestInArray(this, requestIndex);
	}
}