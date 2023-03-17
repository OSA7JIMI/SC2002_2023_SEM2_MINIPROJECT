package sc2002Project;
import java.util.Scanner;

public class Supervisor extends User{
	private Project[] projectArray;
	private int numProject;
	private int numProjectCreated = 0;
	public Supervisor () {
		this.numProject = 0;
	}
	public void createProject() {
		System.out.println("Please key in the project title: ");
		Scanner sc = new Scanner (System.in);
		Project project = new Project();
		projectArray[numProjectCreated ++] = project;
		project.projectTitle = sc.nextLine();
		project.s1 = this;
		if(this.numProject >= 2) project.status = "unavailable";
		else project.status = "available";
		
	}
	public void viewAllProject() {
		int i;
		for(i = 0; i < projectArray.length; i++) {
			System.out.print("ProjectID: " + projectArray[0].projectID);
			System.out.print("Project Title: " + projectArray[0].projectTitle);
			System.out.print("Project Status" + projectArray[0].status);
			System.out.println("Student: " + projectArray[0].s);
		}
		
	}
	public void changeTitle() {
		int i;
		System.out.println("You got these projects: ");
		for(i = 0; i < projectArray.length; i++) {
			System.out.print("ProjectID: " + projectArray[i].projectID);
			System.out.print("Project Title: " + projectArray[i].projectTitle);
			System.out.print("Project Status" + projectArray[i].status);
			System.out.println("Student: " + projectArray[i].s);
		}
		System.out.println("Please enter the project ID you want to change title: ");
		Scanner sc = new Scanner (System.in);
		int projectId = sc.nextInt();
		System.out.println("Please enter the new title: ");
		String newTitle = sc.nextLine();
		for(i = 0; i < projectArray.length; i++) {
			if(projectArray[i].projectID == projectId]) {
				projectArray[i].projectTitle = newTitle;
			}
		}
		System.out.println("Down! The new projects list is: ");
		for(i = 0; i < projectArray.length; i++) {
			System.out.print("ProjectID: " + projectArray[i].projectID);
			System.out.print("Project Title: " + projectArray[i].projectTitle);
			System.out.print("Project Status" + projectArray[i].status);
			System.out.println("Student: " + projectArray[i].s);
		}
	}
	public void  transfer() {
		System.out.println("Please enter the projectID you want to transfer to another supervisor: ");
		Scanner sc = new Scanner (System.in);
		int projectID = sc.nextInt();
		System.out.println("Please enter the replacement supervisorId");
		int replacementId = sc.nextInt();
		Request request = new Request();
		
	
	}
	public void managePendingRequest() {
		
	}
	public void viewAllHisRequest() {
		
	}
}
