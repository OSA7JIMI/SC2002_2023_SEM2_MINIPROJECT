package databaseProject;

public class DatabaseProjectAccessor {
	public static void addProject(Project p) {
		ProjectArray.projectArray.add(p);
	}
	
	public static void updateProjectStatusInDatabase(int id, int status) {
		Project p = ProjectArray.projectArray.get(id-1);
		p.status = status;
		ProjectArray.projectArray.set(id-1, p);
	}
}
