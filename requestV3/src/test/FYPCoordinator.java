package OOP_Group_Project;

import java.util.Scanner;

public class FYPCoordinator extends Supervisor{
	

	public FYPCoordinator(String name, String email, String ID) {
		super(name,email,ID);
		isFYP = true;
		
	}
	public void settleRequests(){
		if(super.getincomingRequest().size() == 0) {
			System.out.println("There's no incoming request!");
		}
		System.out.println("Enter the student ID to settle his/her request");
		String stuID = sc.next();
		int i, ID = 0;
		for(i = 0; i < super.getincomingRequest().size();i++) {
			if(DatabaseRequestAccessor.getRequest(super.getincomingRequest().get(i)).senderID == stuID) {
				ID = super.getincomingRequest().get(i);
				break;
		    }
	    }
		if(ID == 0) {
			System.out.println("This student didn't make request to you");
		}
		else {
			Request r = DatabaseRequestAccessor.getRequest(ID);
			System.out.println("Enter 0 to reject, 1 to approve");
			int choice = sc.nextInt();
			if(choice==0) {
				r.settleRequest(false);
			}
			else {
				r.settleRequest(true);
			}
		}
	}
	
	public void displayOptions() {
		int leave =0;
		Scanncer sc = new Scanner(System.in);
		while(!leave) {
			System.outprintln("Enter /leave to leave FYP coordinator options");
			System.outprintln("Enter /viewmyrequests to view your requests");
			System.outprintln("Enter /viewallrequests to view all requests");
			System.outprintln("Enter /viewmyprojects to view your projects");
			System.outprintln("Enter /viewallprojects to view all projects");
			System.outprintln("Enter /settlerequests to settle requests");
			System.outprintln("Enter /projectdetails to generate project details of a project");
			
			String choice = sc.nextline();
			if(choice.equals("/leave")) {
				leave=1;
			}
			else if(choice.equals("/viewmyrequests")) {
				viewMyRequests();
			}
			else if(choice.equals("/viewallrequests")) {
				viewAllRequests();
			}
			else if(choice.equals("/viewmyprojects")) {
				viewMyProjects();
			}
			else if(choice.equals("/viewallprojects")) {
				viewAllProjects();
			}
			else if(choice.equals("settlerequests")) {
				settleRequests();
			}
			else if(choice.equals("/projectdetails")) {
				System.out.println("Enter project id:");
				do {
					projectid = sc.nextInt();
				}while(projectid < 1 || projectid > DatabaseProjectAccessor.getsize());
				this.generateProjectDetails(projectid); // Need to minus 1 or not?
			}
			else {
				System.out.println("Invalid option chosen. Please try again.");
			}
		}
	}
	public viewMyRequests() {
		super.viewrequests();
	}
	public void viewAllRequests() {
		DatabaseProjectAccessor.viewAllRequests();
	}
	public void viewMyProjects() {
		super.viewprojects();
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
