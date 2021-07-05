package chap06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value = "/hello.do",  method = RequestMethod.GET) //get방식으로만 사용가능
	public String hello(Model model) {
		model.addAttribute("name", "만수야 안뇽!");
		return "hello";
	}
	@PostMapping("/hello2.do") //postMapping은 get방식은 접속이 안됨-> 주소에 기입해서 들어갈수 없음
	public String hello2(Model model) {
		model.addAttribute("name", "만수야 안뇽!");
		return "hello";
	}
	@GetMapping("/board.write.do")
	public String write(Model model) {					
		model.addAttribute("name", "신짱구");
		return "hello";								// 주소가 같더라도 get과 post방식으로 따로 매핑하면 오류 안뜸
	}
	@PostMapping("/board.write.do")
	public String write2(Model model) {
		model.addAttribute("name", "신짱구");
		return "hello";
	}

}
