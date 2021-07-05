package chap02;

import java.time.LocalDateTime;
import java.util.Map;

public class MemberListService {
	private MemberDAO memberDAO;
	
	public MemberListService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public Map<String, Member> selectList(){
		return memberDAO.selectList();
	}
	
}
