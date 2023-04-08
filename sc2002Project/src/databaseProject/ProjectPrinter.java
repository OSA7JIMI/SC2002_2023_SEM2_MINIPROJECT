package databaseProject;

import test.Project;

public class ProjectPrinter {
	public static void printAllProject(int projectID) {
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
		int check = 0;
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getStatus()==0) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.println("Status : "+p.getStatus());
				System.out.println(" ");
				check = 1;
			}
		}
		if(check == 0) {
			System.out.println("No available projects!");
		}
	}
	
	public static void printAllBasedOnSupervsior(String supervisorID) {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getSupervisorID().equals(supervisorID)) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.println("Status : "+p.getStatus());
				System.out.println(" ");
			}
		}
	}
	public static void printAllBasedOnStudent(String studentID) {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getStudentID().equals(studentID)) {
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
}