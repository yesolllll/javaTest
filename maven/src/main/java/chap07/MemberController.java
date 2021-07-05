package chap07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
