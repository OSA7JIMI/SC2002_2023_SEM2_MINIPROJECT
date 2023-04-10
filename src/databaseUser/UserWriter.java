package databaseUser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import test.FYPCoordinator;
import test.Student;
import test.Supervisor;
import test.User;

/**
 * Updates the external record of Users
 * @author A34_3
 * @version 9 Apr 2023
 */
public class UserWriter {

	/**
	 * Iterates through the local array and writes to the csv
	 */
	public static void run() {
		try {
			
			PrintWriter out = new PrintWriter(new FileWriter("databases/Fstudent_list.csv", false));
			out.printf("Name, Email, DeregStatus, password\n");
			for(int i=0; i<databaseUserAccessor.getSize(); i++) {
				User u = databaseUserAccessor.getUserByIndex(i);
				if(!(u instanceof Student)) {
					continue;
				}
				Student s = (Student)u;
				String name = s.getName();
				String email = s.getEmail();
				String password = s.getPassword();
				int dereg = 0;
				if(s.getDeregister()) dereg = 1;
				out.printf("%s,%s,%d,%s\n", name, email, dereg, password);
			}
			out.close();
			
			out = new PrintWriter(new FileWriter("databases/Ffaculty_list.csv", false));
			out.printf("Name, Email, password\n");
			for(int i=0; i<databaseUserAccessor.getSize(); i++) {
				User u = databaseUserAccessor.getUserByIndex(i);
				if(!(u instanceof Supervisor)) {
					continue;
				}
				if(u instanceof FYPCoordinator) continue;
				Supervisor s = (Supervisor)u;
				String name = s.getName();
				String email = s.getEmail();
				String password = s.getPassword();
				out.printf("%s,%s,%s\n", name, email, password);
			}
			out.close();
			
			out = new PrintWriter(new FileWriter("databases/FFYP_coordinator.csv", false));
			out.printf("Name, Email, password\n");
			for(int i=0; i<databaseUserAccessor.getSize(); i++) {
				User u = databaseUserAccessor.getUserByIndex(i);
				if(!(u instanceof FYPCoordinator)) {
					continue;
				}
				FYPCoordinator s = (FYPCoordinator)u;
				String name = s.getName();
				String email = s.getEmail();
				String password = s.getPassword();
				out.printf("%s,%s,%s\n", name, email, password);
			}
			
			out.close();
			
		} catch (IOException e) {
			
			System.out.println("File not found");
			e.printStackTrace();
			
		} finally {
			
			System.out.println("User CSV record has been updated");
		}
	}

}