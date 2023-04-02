package Request;

import Test.Project;

import Test.Student;
import Test.Supervisor;
import databaseProject.DatabaseProjectAccessor;
import databaseUser.databaseUserAccessor;

public class RequestTransfer extends Request{
	public RequestTransfer(int projectID, String supervisorID) {
		this.pending = true;
		this.type = 3;
		this.replacementID = supervisorID;
		this.projectID = projectID;
	}
	@Override
	public void settleRequest(boolean approval) {

		//added to check if replacement supervisor has capacity to take 1 more proj
		Supervisor replacement = (Supervisor)databaseUserAccessor.getUser(replacementID);

		if(replacement.getNumProject() >= 2){
			RequestPrinter.alertMessage();
			int choice = sc.nextInt();
			if(choice==0) {dblapproval =false;}
			else {dblapproval =true;}
		}

		this.pending = false;
		this.approval = approval;
		if(approval == true && dblapproval == true) { //changed here to include dblapproval
			Project p = DatabaseProjectAccessor.getProject(projectID);
			p.setSupervisor(replacementID);
			DatabaseProjectAccessor.updateProjectInDatabase(p);
			Student s = (Student)databaseUserAccessor.getUser(senderID);
			s.setSupervisor(this.replacementID);
			Supervisor original = (Supervisor)databaseUserAccessor.getUser(receiverID);
			//Supervisor replacement = (Supervisor)databaseUserAccessor.getUser(replacementID);
			original.removeProjectFromArray(projectID);
			replacement.addProjectToArray(projectID);
		}
	}
}
