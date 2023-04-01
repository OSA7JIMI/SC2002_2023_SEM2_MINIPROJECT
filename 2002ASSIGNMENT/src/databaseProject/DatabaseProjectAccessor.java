package databaseProject;

import Test.Project;

public class DatabaseProjectAccessor {
	public static void addProject(Project p) {
		ProjectArray.projectArray.add(p);
		ProjectArray.size++;
	}
	
	public static Project getProject(int id) {
		return ProjectArray.projectArray.get(id);
	}
	
	public static void updateProjectStatusInDatabase(int id, int status) {
		Project p = ProjectArray.projectArray.get(id);
		p.setStatus(status);
		ProjectArray.projectArray.set(id-1, p);
	}
	
	public static int getSize() {
		return ProjectArray.size;
	}
	
	/*public static void viewAllProject() {
		System.out.println("---Viewing all projects---");
		for(int i=0; i<ProjectArray.projectArray.size(); i++) {
			System.out.println("Project title: "+ProjectArray.projectArray.get(i).getTitle());
			System.out.println("Status: "+ProjectArray.projectArray.get(i).getStatus());
			System.out.println("----------");
		}
	}*/
	public static void updateProjectInDatabase(Project p) {
		ProjectArray.projectArray.set(p.getID(), p);
	}
}
