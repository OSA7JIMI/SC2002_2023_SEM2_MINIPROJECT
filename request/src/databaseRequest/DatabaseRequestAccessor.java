package databaseRequest;

import request.Request;

public class DatabaseRequestAccessor extends RequestArray{
	public static void addToArray(Request r) {
		requestArray.add(r);
	}
	
	public static Request get(int index) {
		return requestArray.get(index);
	}
	
	public static void settle(Request r) {
		r.settleRequest();
	}
	
}
