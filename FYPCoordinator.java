package OOP_Group_Project;

import java.util.Scanner;

public class FYPCoordinator extends Supervisor{

	public FYPCoordinator(String name, String email, String ID) {
		super(name, email, ID);
		this.isFYP = true;
	public void settleRequests(){
		int i=0,j=1;
		Scanncer sc = new Scanner(System.in);
		for(int i=0;i<incoming.length;i++) {
			if(incoming[i].getpending()==1) {
				System.out.println("Request " + j + ": " + "type: " incoming[i].gettype() + 
						"sender: " + DatabaseUserAccessor.getUser(incoming[i].getsenderindex())+
						"Receiver: " + DatabaseUserAccessor.getUser(incoming[i].getreceiverindex()));
				j++;
			}
			
		}
		System.out.println("Enter request number or -1 to exit");
		int choice = sc.nextInt();
		//tbc
		
		
		
	}
	public void viewAllRequests() {
		DatabaseProjectAccessor.viewAllRequests();
	}
	public void viewAllProjects() {
		DatabaseProjectAccessor.viewAllProjects();
	}
	public void generateProjectDetails(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		System.out.println("projectID: " p.getprojectID());
		System.out.println("project title: " p.getprojectTitle());
		System.out.println("Status: " p.getstatus());
		
	}
	
}
