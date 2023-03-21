package request;

import test.Project;

public class RequestDeregister extends Request{
	public RequestDeregister(int senderIndex) {
		this.pending = true;
		this.type = 2;
		this.senderIndex = senderIndex;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
	}
}
