package Test;

public class Project {
	//0 for available, 1 for reserved, 2 for taken
	private int status;
	private int projectID;
	private String projectTitle;
	private String studentID;
	private String supervisorID;

	public Project(int projectID,String projectTitle, int status, String supervisorID) {
		this.projectID = projectID;
		this.projectTitle = projectTitle;
		this.status = status;
		this.supervisorID = supervisorID;
	}
	public int getID() {
		return projectID;
	}
	//NEW
	public String getTitle() {
		return this.projectTitle;
	}
	//NEW
	public int getStatus() {
		return this.status;
	}
	//NEW
	public void setStatus(int status) {
		this.status = status;
	}
	//NEW
	public Supervisor getSupervisor() {
		return this.getSupervisor();
	}
	//NEW
	public void setTitle(String newTitle) {
		this.projectTitle = newTitle;
	}
	//NEW
	public void setSupervisor(String supervisorID) {
		this.supervisorID = supervisorID;
	}
}