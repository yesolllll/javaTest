package chap09;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor ={Throwable.class})
	public int insert(MemberVO vo, HttpServletRequest req) throws Exception  {
		int r=1;
//		try {
		System.out.println(vo.getMno());
		dao.insert(vo);						//위 아래 번호는 다를 것임
		System.out.println(vo.getMno());
		//(호출하면)vo객체에 mno가 set이 되어있는 상태
		String[] school =req.getParameterValues("school");
		String[] year =req.getParameterValues("year");
		Map map = new HashMap();
		map.put("members_mno", vo.getMno());
		if(true) throw new Exception();
		for(int i=0; i<school.length; i++) {
			if(!"".equals(school[i]) && !"".equals(year[i])) {
			map.put("school", school[i]);
			map.put("year", year[i]);
			dao.insertSchool(map);
			}
		}
//		r = 1;
//	}catch (Exception e) {
//		r=0;
//	}	
		return r;
	}
}
