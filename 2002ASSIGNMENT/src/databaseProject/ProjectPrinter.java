package databaseProject;

import Test.Project;

public class ProjectPrinter {
	public static void printALlProject(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		System.out.println("-----------------");
		System.out.println("ID : "+p.getID());
		System.out.println("Title : "+p.getTitle());
		System.out.println("Student : "+p.getStudentID());
		System.out.println("Supervisor : "+p.getSupervisorID());
		System.out.println("Status : "+p.getStatus());
		System.out.println(" ");
	}
	
	public static void printAllAvailable() {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getStatus()==0) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.println("Status : "+p.getStatus());
				System.out.println(" ");
			}
		}
	}
	
	public static void printAllBasedOnSupervsior(int userID) {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getID()==userID) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.println("Status : "+p.getStatus());
				System.out.println(" ");
			}
		}
	}
	
	public static void printAllBasedOnStatus(int status) {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getStatus()==status) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.println("Status : "+p.getStatus());
				System.out.println(" ");
			}
		}
	}
	public static void printAllBasedOnStatus(string title) {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getTitle().equals(title)) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.println("Status : "+p.getStatus());
				System.out.println(" ");
				break;
			}
		}
	}
}
