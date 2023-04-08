package databaseUser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Request.Request;
import test.Project;
import test.Student;
import test.User;

public class StudentWriter {

	public static void run() {
		try {
			
			PrintWriter out = new PrintWriter(new FileWriter("C:/Users/Dreamcore/OneDrive/Desktop/2002CSV/Fstudent_list.csv", false));
			out.printf("Name, Email, DeregStatus\n");
			for(int i=0; i<databaseUserAccessor.getSize(); i++) {
				User u = databaseUserAccessor.getUserByIndex(i);
				if(!(u instanceof Student)) {
					continue;
				}
				Student s = (Student)u;
				String name = s.getName();
				String email = s.getEmail();
				int dereg = 0;
				if(s.getDeregister()) dereg = 1;
				out.printf("%s,%s,%d\n", name, email, dereg);
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
