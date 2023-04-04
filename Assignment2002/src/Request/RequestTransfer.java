package Request;

import java.util.Scanner;

import Test.Project;

import Test.Student;
import Test.Supervisor;
import databaseProject.DatabaseProjectAccessor;
import databaseProject.ProjectStatusUpdator;
import databaseRequest.RequestPrinter;
import databaseUser.databaseUserAccessor;

public class RequestTransfer extends Request{
	
	Scanner sc = new Scanner(System.in);
	
	public RequestTransfer(int projectID, String supervisorID) {
		this.pending = true;
		this.type = 3;
		this.replacementID = supervisorID;
		this.projectID = projectID;
	}
	public RequestTransfer() {
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
			//Student s = (Student)databaseUserAccessor.getUser(senderID);
			//s.setSupervisor(this.replacementID);
			Supervisor original = (Supervisor)databaseUserAccessor.getUser(senderID);
			//Supervisor replacement = (Supervisor)databaseUserAccessor.getUser(replacementID);
			original.removeProjectFromArray(projectID);
			original.decrementNumProject();
			ProjectStatusUpdator.setAllProjectsAvailable(original.getUserID());
			replacement.incrementNumProject();
			replacement.addProjectToArray(projectID);
			if(replacement.getNumProject()>=2) {
				ProjectStatusUpdator.setAllProjectsUnavailable(replacement.getUserID());
			}
		}
	}
}