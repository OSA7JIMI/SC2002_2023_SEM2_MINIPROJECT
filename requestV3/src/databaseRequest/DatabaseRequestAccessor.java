package databaseRequest;

import request.Request;

public class DatabaseRequestAccessor {
	public static void addRequest(Request r) {
		RequestArray.requestArray.add(r);
	}
	//NEW
	public static void updateRequestInArray(Request r, boolean approval) {
		int index = RequestArray.requestArray.indexOf(r);
		Request r1 = RequestArray.requestArray.get(index);
		r1.pending = false;
		r1.approval = approval;
		RequestArray.requestArray.set(index, r1);
	}
}
