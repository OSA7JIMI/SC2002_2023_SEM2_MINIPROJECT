package databaseRequest;

import Request.Request;

/**
 * 
 * @author A34_3
 *
 */
public class DatabaseRequestAccessor {

	/**
	 * 
	 * @param r the request to be added to database
	 * @return the index that the request is stored at
	 */
	public static int addRequest(Request r) {
		RequestArray.requestArray.add(r);
		// RequestArray.requestArray.size();
		return RequestArray.requestArray.size() - 1;

	}

	/**
	 * 
	 * @param r     the request to be updated in database
	 * @param index the index of the request
	 */
	public static void updateRequestInArray(Request r, int index) {
		RequestArray.requestArray.set(index, r);
	}

	/**
	 * 
	 * @param r a request
	 * @return its index
	 */
	public static int getIndex(Request r) {
		return RequestArray.requestArray.indexOf(r);
	}

	/**
	 * 
	 * @param index the index of a request
	 * @return the desired request
	 */
	public static Request getRequest(int index) {
		return RequestArray.requestArray.get(index);
	}

	/**
	 * 
	 * @return number of requests in database
	 */
	public static int getSize() {
		return RequestArray.requestArray.size();
	}
}