package databaseProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Test.Project;
import Test.Student;
import Test.Supervisor;
import databaseUser.databaseUserAccessor;

public class ProjectReader {

	private static final String COMMA_DELIMITER = ",";

	public static void run() {
		ProjectReader database = new ProjectReader();
		database.init_database();
		
		//DEBUG
		System.out.println("project array size is "+DatabaseProjectAccessor.getSize());
		//DEBUG
		
	}

	
	public  void init_database() {
		String Project_path = "C:/Users/user/Desktop/2002CSV/Fproject_list.txt";
		read_csv_to_records(Project_path);
	}
	
	public void read_csv_to_records(String file_path) {
		try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
		    String line;
		    //br.readLine();
		    while ((line = br.readLine()) != null) {
		    	if(line.isEmpty()) break;
		        String[] values = line.split(COMMA_DELIMITER);
		        if(values.length!=0) {		
		        	
		        	int ID = Integer.parseInt(values[0]);
		        	int status = Integer.parseInt(values[1]);
		        	String title = values[2];
		        	String studentID = null;
		        	if(!values[3].equals("null")) studentID = values[3];
		        	String supervisorID = values[4];
		        	
		        	Project p = new Project(ID, title, status, supervisorID);
		        	p.setStudent(studentID);
		        	DatabaseProjectAccessor.addProject(p);
		  
		        	Supervisor s = (Supervisor) databaseUserAccessor.getUser(supervisorID);
		        	s.addProjectToArray(ID);
		        	
		        	if(studentID!=null) {
		        		Student stu = (Student) databaseUserAccessor.getUser(studentID);
		        		stu.setProject(ID);
		        		s.incrementNumProject();
		        	}
		       
		        }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}