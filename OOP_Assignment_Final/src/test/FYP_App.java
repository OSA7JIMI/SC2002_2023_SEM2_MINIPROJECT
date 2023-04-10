package test;

import java.util.Scanner;

import databaseUser.UserWriter;
import databaseUser.UserReader;
import databaseUser.databaseUserAccessor;

import databaseProject.ProjectReader;
import databaseProject.ProjectWriter;
import databaseRequest.RequestReader;
import databaseRequest.RequestWriter;

/**
 * 
 * @author A34_3
 *
 */
public class FYP_App {
	
	/**
	 * 
	 * @param args main 
	 */
	public static void main(String[] args) {
		//System.out.println("-----DEBUG-----");
		
		UserReader.run();
		ProjectReader.run();
		RequestReader.run();
		
		System.out.println("Records have been loaded into system");
		
		System.out.println();
		
		boolean end = false;
			
		while(!end){
		      System.out.println("Welcome to the FYP registration system!");
		      //System.out.println("Please enter your user ID to log in: ");
		      Scanner scan = new Scanner(System.in);
		      //String ID = scan.nextLine();
		      //User currentUser;
		      int checkhere = 0;
		      String ID = "ID";
		      while(checkhere == 0) {
		    	  try {
			    	  System.out.println("Please enter your user ID to log in: ");
			    	  ID = scan.nextLine();
			    	  databaseUserAccessor.getUser(ID);
			    	  checkhere = 1;
			    	  }
		    	  catch(Exception e) {
		    		  System.out.println("Invalid username. Please enter again.");
		    		  }
		    	  }
		      User currentUser = databaseUserAccessor.getUser(ID);

		      System.out.println("Please enter your password: ");
		      String password = scan.nextLine();

		      while(!password.equals(currentUser.getPassword())) {
		        System.out.println("Password is incorrect, please try again");
		        password = scan.nextLine();
		      }

		      System.out.println("Welcome "+ currentUser.getName()+ ", you have successfully logged in");

		      currentUser.displayOptions();
		      System.out.println(); //for visibility				
		      ProjectWriter.run();
		      RequestWriter.run();
		      UserWriter.run();
		      System.out.println(); //for visibility
		      System.out.println("Press enter to continue, type /end to end");
		      String check = scan.nextLine();
		      if(check.equals("/end")) {end = true;}
		    }
		
		
		
		System.out.println("program ending");
	}
}