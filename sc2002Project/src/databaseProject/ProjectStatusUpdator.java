package databaseProject;

import test.Project;

public class ProjectStatusUpdator {
	public static void setAllProjectsUnavailable(String supervisorID) {
		for(int i=0; i<DatabaseProjectAccessor.getSize(); i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getSupervisorID().equals(supervisorID) && p.getStatus()==0) {
				p.setStatus(3);
				DatabaseProjectAccessor.updateProjectInDatabase(p);
			}
		}
	}
	
	public static void setAllProjectsAvailable(String supervisorID) {
		for(int i=0; i<DatabaseProjectAccessor.getSize(); i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getSupervisorID().equals(supervisorID) && p.getStatus()==3) {
				p.setStatus(0);
				DatabaseProjectAccessor.updateProjectInDatabase(p);
			}
		}
	}
}
