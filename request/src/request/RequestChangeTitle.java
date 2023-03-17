package request;

import databaseRequest.DatabaseRequestAccessor;

public class RequestChangeTitle extends Request{
	public RequestChangeTitle(String newTitle) {
		this.pending = true;
		this.type = 0;
		this.Title = newTitle;
	}
	
	public static Request viewRequestByIndex(int index) {
		return DatabaseRequestAccessor.get(index);
	}
	
	public void settleRequest() {
		return;
	}
}
