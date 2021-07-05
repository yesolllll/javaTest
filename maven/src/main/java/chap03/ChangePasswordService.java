package chap03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ChangePasswordService {
	@Autowired
	@Qualifier("dao")
	private MemberDAO memberDAO;
	
//	public void setMemberDAO(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO; 
//	}
	public void ChangePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDAO.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
	}
}
