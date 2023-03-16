package sc2002_assignment;
import java.util.Scanner;

public class Student extends User {
	private String p;
	
	
	Student(String name, String email, String ID, String password) {
		super(name, email, ID, password); // make sure same order in User class
	}
	
	
	public void viewAllProject() {
//		edit based on database 
		
		String[] database_array = new String[10];
		database_array[0] = "a";
		for(int i = 0; i<database_array.length; i++) {
			System.out.println(database_array[i]);
		}
		
	}
	
	
	public void viewProject() {
//		edit based on project class
		
		if (p == null) {
			System.out.println("You have not selected a project.");
		}
		else {
			System.out.println("Your project title is: " + p);
		}
	}
	
	
	public void changeTitle() {
		Scanner s = new Scanner(System.in);
	    System.out.println("Enter new title:");
	    String newtitle = s.nextLine();
	   
//	    request the change in title  
	}
	
	
	public void allocate() {
		Scanner x = new Scanner(System.in);
		System.out.println("Select your project:");
		String projselected = x.nextLine();
	
//		send request
		System.out.println("Registering project request sent");
	}
	
	
	public void deregister() {
		System.out.println("Deregistering project request sent");
		
//		send request
	}
	
	
	public void viewAllRequests() {
		String[] database_arrayR = new String[10];
		database_arrayR[0] = "a";
		for(int i = 0; i<database_arrayR.length; i++) {
			System.out.println(database_arrayR[i]);
		}
	}
	
	
}
