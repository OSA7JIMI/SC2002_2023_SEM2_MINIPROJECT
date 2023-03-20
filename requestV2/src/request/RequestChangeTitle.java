package request;

public class RequestChangeTitle extends Request{
	public RequestChangeTitle(String newTitle, int senderIndex) {
		this.pending = true;
		this.type = 0;
		this.Title = newTitle;
		this.senderIndex = senderIndex;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
	}
}