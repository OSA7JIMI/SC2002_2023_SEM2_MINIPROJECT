package request;

import test.Project;

public class RequestDeregister extends Request{
	public RequestDeregister() {
		this.pending = true;
		this.type = 2;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
	}
}
