package databaseUser;

import java.util.List;
import java.util.stream.Collectors;

import test.User;

/**
 * Accessor and modifier functions for the local User array
 * @author A34_3
 *
 */
public class databaseUserAccessor {

	/**
	 * Retrieves a User object
	 * @param userID ID of a user
	 * @return the desired user
	 */
	public static User getUser(String userID) {
		List<User> gottem = UserArray.records.stream().filter(u -> u.getUserID().equals(userID))
				.collect(Collectors.toList());
		return gottem.get(0);
	}

	/**
	 * Retrieves a User object 
	 * @param index the index of a user
	 * @return the desired user
	 */
	public static User getUserByIndex(int index) {
		return UserArray.records.get(index);
	}

	/**
	 * Adds a User object to the local array
	 * @param u the user to be added to database
	 */
	public static void addUser(User u) {
		UserArray.records.add(u);
	}

	/**
	 * Getter
	 * @return the number of users in database
	 */
	public static int getSize() {
		return UserArray.records.size();
	}
	
	/**
	 * Checks for invalid User exception  
	 * @param ID user ID
	 * @return true if user ID belongs to a User object in the local array
	 */
	public static boolean isUserValid(String ID) {
		try {
	    	  databaseUserAccessor.getUser(ID);
	    	  return true;
		}
		catch(Exception e) {
  		  System.out.println("Invalid ID. Please enter again.");
  		  return false;
  		}
	}
}