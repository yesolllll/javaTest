package chap09;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface MemberService {
	List<MemberVO> selectList();
	MemberVO login(MemberVO vo);
	//MemberVO mypage(int mno);
	String mypage( HttpSession sess, Model model);
	int update(MemberVO vo);
	int insert(MemberVO vo, HttpServletRequest req) throws Exception;
}
