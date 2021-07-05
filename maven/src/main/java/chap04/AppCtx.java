package chap04;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAO();
	}
	
	@Bean
	public MemberRegisterService regSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService pwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		return pwdSvc;
	}
	@Bean
	public MemberListService listSvc() {
		return new MemberListService();
	}
	
	@Bean
	public MemberInfoService infoSvc() {
		return new MemberInfoService();
	}
}
