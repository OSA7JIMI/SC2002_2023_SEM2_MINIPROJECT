package databaseUser;

import test.FYPCoordinator;
import test.Student;
import test.Supervisor;
import test.User;

import java.io.*;  

public class UserReader {

	private static final String COMMA_DELIMITER = ",";
	public static FYPCoordinator fyp;

	public static void run() {
		UserArray database = new UserArray();
		database.init_database();
		
		//DEBUG
		//System.out.println("test");
		//DEBUG
		
		User.setFYPcoor(fyp);
	}

	
	private void init_database() {
		String FYP_path = "/Users/leaf/NTU 12/SC2002/FFYP_coordinator.csv";
		read_csv_to_records(FYP_path, "FYP");
		String student_path = "/Users/leaf/NTU 12/SC2002/Fstudent_list.csv";
		read_csv_to_records(student_path, "student");
		String supervisor_path = "/Users/leaf/NTU 12/SC2002/Ffaculty_list.csv";
		read_csv_to_records(supervisor_path, "supervisor");
	}
	
	private void read_csv_to_records(String file_path, String user_type) {
		try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        if(values.length!=0) {
		        	String userid = values[1].split("@")[0];
		        	
		        	//DEBUG
		        	
		        	//DEBUG
		        	
		        	if (user_type == "student") {
		        		Student stu = new Student(values[0],values[1],userid);
		        		DatabaseUserAccessor.addUser(stu);
		        	}
		        	else if (user_type == "supervisor") {
		        		Supervisor sup = new Supervisor(values[0],values[1],userid);
		        		DatabaseUserAccessor.addUser(sup);
		        	}
		        	else if (user_type == "FYP") {
		        		fyp = new FYPCoordinator(values[0],values[1],userid);
		        		DatabaseUserAccessor.addUser(fyp);
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
