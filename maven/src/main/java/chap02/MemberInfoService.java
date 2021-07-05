package chap02;

import java.time.LocalDateTime;
import java.util.Map;

public class MemberInfoService {
	private MemberDAO memberDAO;
	
	public MemberInfoService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public Member selectOne(String email){
		return memberDAO.selectByEmail(email);
	}
	
}
