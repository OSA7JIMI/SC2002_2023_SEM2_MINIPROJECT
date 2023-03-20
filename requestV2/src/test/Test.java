package test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Supervisor supervisor = new Supervisor("supervisor", "supervisor@123", "supervisor2123");
		Student student = new Student("student", "studentt@123", "student123", supervisor);
		
		student.changeTitle();
		student.viewAllRequests();
		System.out.println("-----------------------");
		supervisor.viewAllIncoming();
		System.out.println("-----------------------");
		supervisor.settleIncomingRequest(0);
		System.out.println("-----------------------");
		supervisor.viewAllIncoming();
		System.out.println("-----------------------");
		student.viewAllRequests();
	}

}
