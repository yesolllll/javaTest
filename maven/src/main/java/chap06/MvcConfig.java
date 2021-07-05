package chap06;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages= {"chap06"})
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
		conf.enable(); //-> 내가 설정하지 않은것들은 톰캣이 처리하도록 하는 메소드 , 작성을 안하면 실행 오류
	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		// jsp가 있는 경로[시작경로] 확장자
		// return " member/MemberList
		//			↓
		// /WEB-INF/view/member/MemberList.jsp
		reg.jsp("/WEB-INF/view/", ".jsp");
	}
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		//비지니스로직이 필요없는 (디자인만 있는 페이지) url과 jsp매핑
		reg.addViewController("/index.do").setViewName("index");
	} //->/index.do라고 요청이 들어오면 index을 리턴
	
	//	@RequestMapping("/")  //web.xml에 서블릿매핑에 *.do 라고 해놓으면 적용이 안됨
	//	public String index() {
	//		return"index";
	//	}
}
