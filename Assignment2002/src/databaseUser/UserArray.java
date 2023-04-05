package databaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Test.FYPCoordinator;
import Test.Student;
import Test.Supervisor;
import Test.User;

import java.io.*;  

public class UserArray {

	private static final String COMMA_DELIMITER = ",";
	static ArrayList<User> records = new ArrayList<User>();
	public static FYPCoordinator fyp;

	public static void run() {
		UserArray database = new UserArray();
		database.init_database();
		
		//DEBUG
		//System.out.println("test");
		//DEBUG
		
		User.setFYPcoor(fyp);
	}

	
	public  void init_database() {
		String FYP_path = "C:/Users/user/Desktop/2002CSV/FFYP_coordinator.txt";
		read_csv_to_records(FYP_path, "FYP");
		String student_path = "C:/Users/user/Desktop/2002CSV/Fstudent_list.txt";
		read_csv_to_records(student_path, "student");
		String supervisor_path = "C:/Users/user/Desktop/2002CSV/Ffaculty_list.txt";
		read_csv_to_records(supervisor_path, "supervisor");
	}
	
	public void read_csv_to_records(String file_path, String user_type) {
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
		        		records.add(stu);
		        	}
		        	else if (user_type == "supervisor") {
		        		Supervisor sup = new Supervisor(values[0],values[1],userid);
		        		records.add(sup);
		        	}
		        	else if (user_type == "FYP") {
		        		fyp = new FYPCoordinator(values[0],values[1],userid);
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