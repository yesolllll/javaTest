package chap02;

public class Assembler {
	private MemberDAO memberDAO;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	private MemberListService listSvc;
	private MemberInfoService infoSvc;
	
	public Assembler() {
		memberDAO = new MemberDAO();
		regSvc = new MemberRegisterService(memberDAO);
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDAO(memberDAO);
		listSvc = new MemberListService(memberDAO);
		infoSvc = new MemberInfoService(memberDAO);
	}

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}


	public MemberRegisterService getRegSvc() {
		return regSvc;
	}


	public ChangePasswordService getPwdSvc() {
		return pwdSvc;
	}
	
	public MemberListService getListSvc() {
		return listSvc;
	}
	
	public MemberInfoService getInfoSvc() {
		return infoSvc;
	}

}
