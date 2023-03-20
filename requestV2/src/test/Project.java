package test;

public class Project {
	//0 for available, 1 for reserved, 2 for taken
	private int status;
	private int projectID;
	private String projectTitle;
	private Student s;
	private Supervisor sp;

	public Project(int projectID,String projectTitle, int status, Student s, Supervisor sp) {
		this.projectID = projectID;
		this.projectTitle = projectTitle;
		this.status = status;
		this.s = s;
		this.sp = sp;
	}
	public int getID() {
		return projectID;
	}
}