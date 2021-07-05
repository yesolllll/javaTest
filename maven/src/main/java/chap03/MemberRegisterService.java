package chap03;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberRegisterService {
	@Autowired
	@Qualifier("dao")
	private MemberDAO memberDAO;
	
//	public MemberRegisterService(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//		//this.memberDAO = new MemberDAO;도 가능하지만 매번 새로운 객체를 생성하게 되고 수정시 들어와서 수정을 해줘야한다.
//	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDAO.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateMemberException("이메일 중복"+req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		
		memberDAO.insert(newMember);
		return newMember.getId();
	}
}
