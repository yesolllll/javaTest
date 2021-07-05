package chap04;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoService {
	@Autowired
	private MemberDAO memberDAO;
	
//	public MemberInfoService(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//	}
	
	public Member selectOne(String email){
		return memberDAO.selectByEmail(email);
	}
	
}
