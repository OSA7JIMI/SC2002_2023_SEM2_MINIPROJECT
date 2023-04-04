package Test;

import java.util.Scanner;

import databaseUser.UserArray;
import databaseUser.databaseUserAccessor;

import databaseProject.ProjectReader;
import databaseProject.ProjectWriter;
import databaseRequest.RequestReader;
import databaseRequest.RequestWriter;

public class FYP_App {

	public static void main(String[] args) {
		System.out.println("-----DEBUG-----");
		
		UserArray.main(args);
		ProjectReader.main(args);
		RequestReader.main(args);
		
		System.out.println("-----DEBUG-----");
		System.out.println();
		
		boolean end = false;
		boolean logout = false;
			
		while(!end){
		      System.out.println("Welcome to the FYP registration system!");
		      System.out.println("Please enter your user ID to log in: ");
		      Scanner scan = new Scanner(System.in);
		      String ID = scan.nextLine();
		      User currentUser = databaseUserAccessor.getUser(ID); // fill in the method chain search function
		      // exception handling if search function fails

		      System.out.println("Please enter your password: ");
		      String password = scan.nextLine();

		      while(!password.equals(currentUser.getPassword())) {
		        System.out.println("Password is incorrect, please try again");
		        password = scan.nextLine();
		      }

		      System.out.println("Welcome,"+ currentUser.getName()+ ", you have successfully logged in");

		      currentUser.displayOptions();
		      System.out.println(); //for visibility
		      System.out.println("Press enter to continue, type /end to end");
		      String check = scan.nextLine();
		      if(check.equals("/end")) {end = true;}
		    }
		
		// TODO update csv
		System.out.println("program ending");
		ProjectWriter.main(args);
		RequestWriter.main(args);
	}
}