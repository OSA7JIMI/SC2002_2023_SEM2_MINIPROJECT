package databaseUser;

import java.util.List;
import java.util.stream.Collectors;

import test.User;

public class databaseUserAccessor {
	public static User getUser(String userID) {
		List<User> gottem = UserArray.records.stream().filter(u -> u.getUserID().equals(userID)).collect(Collectors.toList());
		return gottem.get(0);
	}
	public static User getUserByIndex(int index) {
		return UserArray.records.get(index);
	}
	public static void addUser(User u){
		UserArray.records.add(u);
	}
	public static int getSize() {
		return UserArray.records.size();
	}
}