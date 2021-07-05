package chap08;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberServiceImple implements MemberService {

	@Autowired
	MemberDAO dao;
	
	@Override
	public List<MemberVO> selectList() {
		return dao.selectList();
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return dao.login(vo);
	}
//	@Override
//	public MemberVO mypage(int mno) {
//		return dao.selectOne(mno);
//	}

	@Override
	public String mypage(HttpSession sess, Model model) {
		MemberVO  vo = (MemberVO)sess.getAttribute("memberInfo"); //->세션에서 꺼낸 객체(object)를 vo에 담았음 = 형변환 
		MemberVO m = dao.selectOne(vo.getMno());
		model.addAttribute("vo", m);
		return "member/mypage";
	}

	@Override
	public int update(MemberVO vo) {
		return dao.update(vo);
	}
}
