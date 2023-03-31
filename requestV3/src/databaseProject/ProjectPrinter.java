package databaseProject;

import Test.Project;

public class ProjectPrinter {
	public static void printALlProject(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		System.out.println("-----------------");
		System.out.println("ID : "+p.getID());
		System.out.println("Title : "+p.getTitle());
		System.out.println("Supervisor : "+p.getSupervisor());
		System.out.println("Status : "+p.getStatus());
	}
}
