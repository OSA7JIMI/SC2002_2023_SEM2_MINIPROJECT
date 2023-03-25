package sc2002_assignment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;  

public class UserArray {

	private static final String COMMA_DELIMITER = ",";
	private List<User> records = new ArrayList<User>();

	public static void main(String[] args) {
		UserArray database = new UserArray();
		database.init_database();
	
	}

	
	public void init_database() {
		String student_path = "/Users/tejaswi/eclipse-workspace/studentt/src/sc2002_assignment/Fstudent_list.csv";
		read_csv_to_records(student_path, "student");
		String supervisor_path = "/Users/tejaswi/eclipse-workspace/studentt/src/sc2002_assignment/Ffaculty_list.csv";
		read_csv_to_records(supervisor_path, "supervisor");
		String FYP_path = "/Users/tejaswi/eclipse-workspace/studentt/src/sc2002_assignment/FFYP_coordinator.csv";
		read_csv_to_records(FYP_path, "FYP");
		
	}
	
	public void read_csv_to_records(String file_path, String user_type) {
		try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        if(values.length!=0) {
		        	String userid = values[1].split("@")[0];
		        	if (user_type == "student") {
		        		Student stu = new Student(values[0],values[1],userid);
		        		records.add(stu);
		        	}
		        	else if (user_type == "supervisor") {
		        		Supervisor sup = new Supervisor(values[0],values[1],userid);
		        		records.add(sup);
		        	}
		        	else if (user_type == "FYP") {
		        		FYPCoordinator fyp = new FYPCoordinator(values[0],values[1],userid);
		        		records.add(fyp);
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

