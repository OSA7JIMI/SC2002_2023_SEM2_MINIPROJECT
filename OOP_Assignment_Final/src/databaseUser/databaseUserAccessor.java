package databaseUser;

import java.util.List;
import java.util.stream.Collectors;

import test.User;

/**
 * 
 * @author A34_3
 *
 */
public class databaseUserAccessor {

	/**
	 * 
	 * @param userID ID of a user
	 * @return the desired user
	 */
	public static User getUser(String userID) {
		List<User> gottem = UserArray.records.stream().filter(u -> u.getUserID().equals(userID))
				.collect(Collectors.toList());
		return gottem.get(0);
	}

	/**
	 * 
	 * @param index the index of a user
	 * @return the desired user
	 */
	public static User getUserByIndex(int index) {
		return UserArray.records.get(index);
	}

	/**
	 * 
	 * @param u the user to be added to database
	 */
	public static void addUser(User u) {
		UserArray.records.add(u);
	}

	/**
	 * 
	 * @return the number of users in database
	 */
	public static int getSize() {
		return UserArray.records.size();
	}
}