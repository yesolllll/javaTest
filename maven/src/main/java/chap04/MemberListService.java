package chap04;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberListService {
	@Autowired
	private MemberDAO memberDAO;
	
//	public MemberListService(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//	}
	
	public Map<String, Member> selectList(){
		return memberDAO.selectList();
	}
	
}
