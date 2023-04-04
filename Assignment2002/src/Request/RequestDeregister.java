package Request;

import Test.Project;
import Test.Student;
import databaseUser.databaseUserAccessor;

public class RequestDeregister extends Request{
	public RequestDeregister() {
		this.pending = true;
		this.type = 2;
	}
	@Override
	public void settleRequest(boolean approval) {
		this.pending = false;
		this.approval = approval;
		if(approval==true) {
			Student s = (Student)databaseUserAccessor.getUser(senderID);
			s.setDeregister();
		}
	}
}