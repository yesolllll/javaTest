package chap09;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@Autowired
	MemberService service; //MemberService는 인터페이스타입은 상위 클래스 이기때문에 상관없음 [나중에 수정등등의 문제로 인터페이스를 사용하는건데 imple도 사용가능]
	
	@RequestMapping("/member/index.do")
	public String index(Model model) {
		List<MemberVO> list = service.selectList();
		model.addAttribute("list", list);
		return "member/index";
	}
	  
	@RequestMapping("/member/form.do")
	public String form(MemberVO vo,@CookieValue(value = "cookieEmail", required = false)Cookie cookie) {
		if(cookie != null) {
			vo.setEmail(cookie.getValue());
			vo.setCheckEmail("check");
		}
		return "member/form";
	}
	
	@RequestMapping("/member/login.do")
	public String login(MemberVO vo, HttpSession sess, HttpServletRequest req, HttpServletResponse res) throws Exception {
		//파라미터 받는 방법 4가지중에 선택해서 받으면 됨
		//이 메소드에서는 커맨드 객체 사용
		MemberVO m= service.login(vo);
		if(m != null) {
			//세션에 저장
			//세션객체를 가져오는 방법
			//1.HttpServletRequest
			// HttpSession session = req.getSession(); 
			// getSession(true) : 세션이 존재하지 않으면 새로 생성
			// getSession(false) : 세션이 존재하지 않으면 null을 리턴
	///////////////////////////////////////////////////////////////////////
			//2.매개변수로 HttpSession
			//세션에 저장
			sess.setAttribute("memberInfo", m); //-> 서버에 저장
			//쿠키에 저장
			Cookie cookie = new Cookie("cookieEmail", vo.getEmail());
			cookie.setPath("/");
			if("check".equals(vo.getCheckEmail())) {
				cookie.setMaxAge(60*60*24*365);
			}else {
				cookie.setMaxAge(0); //쿠키는 삭제하는 기능이 없기때문에 0으로 지정
			}
			res.addCookie(cookie);
			return "redirect:index.do";
		}else {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('이메일과 비밀번호가 올바르지 않습니다.');");
			out.println("location.href='form.do';");
			out.println("</script>");
			return null; //<-jsp로 리턴하면 위에 코드가 실행 되지 않고 넘어감
		}
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(Model model, HttpSession sess, HttpServletResponse res) throws Exception{
		sess.invalidate();  
		model.addAttribute("msg","로그아웃되었습니다.");
		model.addAttribute("url","index.do");
		return "include/alert";
	}
	
	@RequestMapping("/member/mypage.do")
	public String mypage(Model model, HttpSession sess)throws Exception {
//		MemberVO  vo = (MemberVO)sess.getAttribute("memberInfo"); //->세션에서 꺼낸 객체(object)를 vo에 담았음 = 형변환 
//		if(vo!=null) {
//		MemberVO m = service.mypage(vo.getMno());
//		model.addAttribute("vo", m);
//		return "member/mypage";
//		}else {
//			model.addAttribute("msg","로그인 후 사용가능합니다  .");
//			model.addAttribute("url","index.do");
//			return "include/alert";
//		}
	return service.mypage(sess, model);
	}
	
	@RequestMapping("/member/update.do")
	public String update(MemberVO vo, Model model, HttpSession sess) {
		int r= service.update(vo);
		if(r ==0) {
			model.addAttribute("msg","수정실패");
			model.addAttribute("url", "index.do");
			return "include/alert";
		}else {
			model.addAttribute("msg","정상적으로 실행 되었습니다.");
			model.addAttribute("url", "index.do");
			return "include/alert";
		}
		
	}
	@RequestMapping("/member/write.do")
	public String write(MemberVO vo)  throws Exception{
			return "member/write";
		}
	@RequestMapping("/member/insert.do")
	public String write(MemberVO vo, HttpServletRequest req)throws Exception {
		if(service.insert(vo, req)==0) {
			req.setAttribute("msg","등록오류");
			req.setAttribute("url", "write.do");
		}else {
			req.setAttribute("msg","등록성공");
			req.setAttribute("url", "index.do");
		}
		return "include/alert";
	}
}
		

