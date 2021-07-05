package chap02;

public class ChangePasswordService {
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO; 
	}
	public void ChangePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDAO.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
	}
}
