package Request;

import databaseProject.DatabaseProjectAccessor;
import databaseRequest.DatabaseRequestAccessor;
import databaseUser.databaseUserAccessor;
import Test.Project;
import Test.Student;

public class RequestAllocate extends Request{
	
	public RequestAllocate(int projectID) {
		this.pending = true;
		this.type = 1;
		this.projectID  = projectID;
	}
	public RequestAllocate() {
	}
	@Override
	public void settleRequest(boolean approval) {
		System.out.println("settling");
		this.pending = false;
		this.approval = approval;
		if(approval==false) {
			this.projectID = -1;
		}else {
			Project p = DatabaseProjectAccessor.getProject(projectID);
			//Get student from userArray, then use the .setProject()
			//method to allocate project to student
			Student s = (Student)databaseUserAccessor.getUser(this.senderID);
			s.setProject(this.projectID);
			p.setStudent(senderID);
		}
		DatabaseRequestAccessor.updateRequestInArray(this, requestIndex);
	}
}