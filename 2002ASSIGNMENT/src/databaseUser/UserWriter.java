package databaseUser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Request.Request;
import test.FYPCoordinator;
import test.Project;
import test.Student;
import test.Supervisor;
import test.User;

public class UserWriter {

	public static void run() {
		try {
			
			PrintWriter out = new PrintWriter(new FileWriter("C:/Users/Dreamcore/OneDrive/Desktop/2002CSV/Fstudent_list.csv", false));
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
			
			out = new PrintWriter(new FileWriter("C:/Users/Dreamcore/OneDrive/Desktop/2002CSV/Ffaculty_list.csv", false));
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
			
			out = new PrintWriter(new FileWriter("C:/Users/Dreamcore/OneDrive/Desktop/2002CSV/FFYP_coordinator.csv", false));
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
			
			System.out.println("Student updated");
		}
	}

}