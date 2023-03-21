package sc2002_assignment;

import java.util.Scanner;

public class FYP_App {

	public static void main(String[] args) {
		// To-do: create User objects from initialization file
		// and add them to UserArray
		boolean end =false;
		
		while(!end) {
			boolean logout = false;
			System.out.println("Welcome to the FYP registration system!");
			System.out.println("Please enter your user ID to log in: ");
			Scanner scan = new Scanner(System.in);
			String ID = scan.nextLine();
			User currentUser = dummy; // fill in the method chain search function
			// exception handling if search function fails

			System.out.println("Please enter your password: ");
			String password = scan.nextLine();

			/*int max_tries=3;
			for(int i =0; i<max_tries; i++) {
				if(password != currentUser.getPassword()) {
				System.out.println("Password is incorrect, "+ (max_tries-i)+ " tries remaining.");
				password = scan.nextLine();
				}
			}*/
			
			// we should ask Li Fang about limiting password tries

			while(password != currentUser.getPassword()) {
				System.out.println("Password is incorrect, please try again");
				password = scan.nextLine();
			}

			System.out.println("You have successfully logged in");

			while(!logout) {
				currentUser.displayOptions();
				System.out.println("Enter /logout to log out of user account");
				System.out.println("Enter /end to end program");
				System.out.println("Press enter to continue");
				if(scan.nextLine() == "/logout") {
					logout = true;
					currentUser = null;
				}
				if(scan.nextLine() == "/end") {
					end = true;
				}
			}
		}
	}
}
