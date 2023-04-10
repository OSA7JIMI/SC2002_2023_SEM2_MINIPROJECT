package test;

/**
 * 
 * @author A34_3
 *
 */
public class Project {
	/**
	 * 0 for available, 1 for reserved, 2 for allocated, 3 for unavailable
	 */
	private int status;
	private int projectID;
	private String projectTitle;
	private String studentID = null;
	private String supervisorID = null;

	/**
	 * Constructor
	 * @param projectID ID of project
	 * @param projectTitle title of project
	 * @param status registration status of project
	 * @param supervisorID ID of supervisor who created the project
	 */
	public Project(int projectID,String projectTitle, int status, String supervisorID) {
		this.projectID = projectID;
		this.projectTitle = projectTitle;
		this.status = status;
		this.supervisorID = supervisorID;
	}
	
	/**
	 * Getter
	 * @return ID of project
	 */
	
	public int getID() {
		return projectID;
	}
	//NEW
	
	/**
	 * Getter
	 * @return title of project
	 */
	public String getTitle() {
		return this.projectTitle;
	}
	//NEW
	
	
	/**
	 * Setter
	 * @param newTitle new title of project
	 */
	public void setTitle(String newTitle) {
		this.projectTitle = newTitle;
	}
	//NEW
	
	/**
	 * Getter
	 * @return status of project
	 */
	public int getStatus() {
		return this.status;
	}
	//NEW
	
	/**
	 * Setter
	 * @param status status of project
	 */ 
	public void setStatus(int status) {
		this.status = status;
	}
	//NEW
	
	/**
	 * Getter
	 * @return ID of supervisor supervising the project
	 */
	public String getSupervisorID() {
		return this.supervisorID;
	}
	//NEW
	
	/**
	 * Setter
	 * @param supervisorID ID of supervisor supervising the project
	 */
	public void setSupervisor(String supervisorID) {
		this.supervisorID = supervisorID;
	}
	//NEW
	
	/**
	 * Getter
	 * @return ID of student allocated to the project
	 */
	public String getStudentID() {
		return this.studentID;
	}
	//NEW

	/**
	 * Setter
	 * @param studentID ID of student allocated to the project
	 */
	public void setStudent(String studentID) {
		this.studentID = studentID;
	}
	
}