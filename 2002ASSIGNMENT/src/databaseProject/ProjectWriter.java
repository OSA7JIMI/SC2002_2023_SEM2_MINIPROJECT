package databaseProject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Test.Project;

public class ProjectWriter {

	public static void main(String[] args) {
		try {
			
			PrintWriter out = new PrintWriter(new FileWriter("C:/Users/Dreamcore/OneDrive/Desktop/2002CSV/Fproject_list.txt", false));
			for(int i=0; i<DatabaseProjectAccessor.getSize(); i++) {
				Project p = DatabaseProjectAccessor.getProject(i);
				int ID = p.getID();
				int status = p.getStatus();
				String title = p.getTitle();
				String studentID = "null";
				if(p.getStudentID()!=null) studentID = p.getStudentID();
				String supervisorID = p.getSupervisorID();
				
				out.printf("%d,%d,%s,%s,%s\n", ID, status, title, studentID, supervisorID);
			}
			
			out.close();
			
		} catch (IOException e) {
			
			System.out.println("File not found");
			e.printStackTrace();
			
		} finally {
			
			System.out.println("end");
		}
	}

}
