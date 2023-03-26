package Request;

import Test.Project;

public class RequestDeregister extends Request{
	public RequestDeregister() {
		this.pending = true;
		this.type = 2;
	}
	public String getType() {
		return "Deregistration";
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
	}
}
