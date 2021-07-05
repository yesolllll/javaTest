package chap06;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	
	//	@RequestMapping("/")  //web.xml에 서블릿매핑에 *.do 라고 해놓으면 적용이 안됨
	//	public String index() {
	//		return"index";
	//	}
	
		//리턴이 없으면 매핑된 url과 동일한 경로 jsp를 포워딩
		//서블릿으로 응답 가능
		@RequestMapping("/test.do")
		public void test(HttpServletResponse res) throws IOException {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out= res.getWriter();
			out.print("메롱메롱약오르지");
			out.print("<script>alert('정상적으로 저장되었습니다.');</script>");
		}
		
		@RequestMapping("/")  //web.xml에 서블릿매핑에 *.do 라고 해놓으면 적용이 안됨
	public String index() {
			return"redirect:index.do";
		}
		@RequestMapping("/form.do")
		public String form() {
			return "/form";
		}
		@RequestMapping("/send.do")
			public String send(Model model, HttpServletRequest req, 
								@RequestParam(value = "name", required = false) String name2, //-> required = false를 사용하면 반드시 있어야하는것이 아닌 항목이 됨,
								@RequestParam(value = "email", required = true) String email2,//  post방식으로 전송을 하면 기본값인 ""이나 0으로 값이 넘어가므로 get방식 을 말하는것임
								@RequestParam(value = "no", required = false, defaultValue = "0") int no,
								MemberVO vo) {
			//★★★★★★파라미터를 받는 방법★★★★★★
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			
			//1.HttpServletRequest
			model.addAttribute("name1", name);
			model.addAttribute("email1", email);
			
			//2.@RequestParam
			//@RequestParam("파라미터명") : 매개변수에 지정 
			model.addAttribute("name2", name2);
			model.addAttribute("email2", email2);
			model.addAttribute("no", no);
			
			//3.커맨드객체
			//chap06에 MemberVO생성후 name과 email getter/setter생성
			model.addAttribute("vo", vo);  // -> 삭제해도 가능 
			System.out.println("취미를 담은 길이"+vo.getHobby().length);
			for(int v :vo.getHobby()) {
				System.out.println(v);
			}
			
			req.setAttribute("id", "sol");
			if( email == null || "".equals(email)) {
				return "form";
			}
				return "send";
		}
		//ModeAndView객체 리턴
		@RequestMapping("/main.do")
		public ModelAndView main() {
			ModelAndView mav = new ModelAndView();
			mav.addObject("name", "홍길동");
			mav.setViewName("main");
			return mav;
		}
}
