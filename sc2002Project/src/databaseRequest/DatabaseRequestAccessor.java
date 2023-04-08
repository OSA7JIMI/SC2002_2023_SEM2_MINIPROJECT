package databaseRequest;

import Request.Request;

public class DatabaseRequestAccessor {
	public static int addRequest(Request r) {
		RequestArray.requestArray.add(r);
		//RequestArray.requestArray.size();
		return RequestArray.requestArray.size() - 1;
		
	}
	//NEW
	public static void updateRequestInArray(Request r, int index) {
		RequestArray.requestArray.set(index, r);
	}
	public static int getIndex(Request r) {
		return RequestArray.requestArray.indexOf(r);
	}
	public static Request getRequest(int index) {
		return RequestArray.requestArray.get(index);
	}
	public static int getSize() {
		return RequestArray.requestArray.size();
	}
}