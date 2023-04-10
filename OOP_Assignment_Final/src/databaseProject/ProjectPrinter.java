package databaseProject;

import test.Project;

/**
 * Methods for printing project information based on various criteria
 * @author A34_3
 * @version 9 Apr 2023
 *
 */
public class ProjectPrinter {
	/**
	 * Prints details of a particular project
	 * @param projectID ID of project
	 */
	public static void printProject(int projectID) {
		Project p = DatabaseProjectAccessor.getProject(projectID);
		System.out.println("-----------------");
		System.out.println("ID : "+p.getID());
		System.out.println("Title : "+p.getTitle());
		System.out.println("Student : "+p.getStudentID());
		System.out.println("Supervisor : "+p.getSupervisorID());
		System.out.print("Status : ");
		switch(p.getStatus()) {
		case 0:
			System.out.print("Available");
			break;
		case 1:
			System.out.print("Reserved");
			break;
		case 2:
			System.out.print("Allocated");
			break;
		case 3:
			System.out.print("Unavailable");
			break;
		}
		System.out.println(" ");
	}
	
	/**
	 * Prints all available projects
	 */
	public static void printAllAvailable() {
		int check = 0;
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getStatus()==0) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.println("Status : Available");
				System.out.println(" ");
				check = 1;
				System.out.println(" ");
			}
		}
		if(check == 0) {
			System.out.println("No available projects!");
		}
	}
	
	/**
	 * Prints projects that are under a particular supervisor
	 * @param supervisorID ID of supervisor
	 */ 
	public static void printAllBasedOnSupervisor(String supervisorID) {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getSupervisorID().equals(supervisorID)) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.print("Status : ");
				switch(p.getStatus()) {
				case 0:
					System.out.print("Available");
					break;
				case 1:
					System.out.print("Reserved");
					break;
				case 2:
					System.out.print("Allocated");
					break;
				case 3:
					System.out.print("Unavailable");
					break;
				}
				System.out.println();
				System.out.println();
			}
		}
	}
	
	/**
	 * Prints project that is allocated to a particular student
	 * @param studentID ID of student
	 */
	public static void printAllBasedOnStudent(String studentID) {
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if (p.getStudentID()==null) {
				System.out.println("This student does not have a project");
				return;
			}
			else if(p.getStudentID().equals(studentID)) {
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.print("Status : ");
				switch(p.getStatus()) {
				case 0:
					System.out.print("Available");
					break;
				case 1:
					System.out.print("Reserved");
					break;
				case 2:
					System.out.print("Allocated");
					break;
				case 3:
					System.out.print("Unavailable");
					break;
				}
				System.out.println();
				System.out.println();
				return;
			}
		}
	}
	
	/**
	 * Prints projects that are a particular status
	 * @param status The desired status
	 */
	public static void printAllBasedOnStatus(int status) {
		int count = 0;
		for(int i=0; i<ProjectArray.size; i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getStatus()==status) {
				count++;
				System.out.println("ID : "+p.getID());
				System.out.println("Title : "+p.getTitle());
				System.out.println("Student : "+p.getStudentID());
				System.out.println("Supervisor : "+p.getSupervisorID());
				System.out.print("Status : ");
				switch(p.getStatus()) {
				case 0:
					System.out.print("Available");
					break;
				case 1:
					System.out.print("Reserved");
					break;
				case 2:
					System.out.print("Allocated");
					break;
				case 3:
					System.out.print("Unavailable");
					break;
				}
				System.out.println();
				System.out.println();
			}
		}
		if(count == 0) System.out.println("No projects with this status");
	}
}