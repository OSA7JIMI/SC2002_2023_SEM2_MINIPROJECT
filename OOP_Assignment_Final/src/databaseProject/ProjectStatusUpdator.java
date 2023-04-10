package databaseProject;

import test.Project;

/**
 * 
 * @author A34_3
 *
 */

public class ProjectStatusUpdator {
	
	/**
	 * Sets all projects under a particular supervisor as unavailable
	 * @param supervisorID ID of the supervisor
	 */
	public static void setAllProjectsUnavailable(String supervisorID) {
		for(int i=0; i<DatabaseProjectAccessor.getSize(); i++) {
			Project p = DatabaseProjectAccessor.getProject(i);
			if(p.getSupervisorID().equals(supervisorID) && p.getStatus()==0) {
				p.setStatus(3);
				DatabaseProjectAccessor.updateProjectInDatabase(p);
			}
		}
	}
	
	/**
	 * Sets all projects under a particular supervisor as available
	 * @param supervisorID ID of the supervisor
	 */
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