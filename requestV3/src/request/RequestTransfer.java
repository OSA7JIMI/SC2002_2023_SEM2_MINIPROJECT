package request;

import test.Supervisor;

public class RequestTransfer extends Request{
	Supervisor replacementS;
	public RequestTransfer(Supervisor s, int senderIndex) {
		this.pending = true;
		this.type = 3;
		this.senderIndex = senderIndex;
		this.replacementS = s;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
	}
}
