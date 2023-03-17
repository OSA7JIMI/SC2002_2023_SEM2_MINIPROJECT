package OOP_Group_Project;

public class Project {
	private enum status {Available,Reserved,Taken};
	private int projectID;
	private String projectTitle;
	private String status;
	private Student s;
	private Supervisor sp;

	public Project(String projectID,String projectTitle, String status, Student s, Supervisor sp) {
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
