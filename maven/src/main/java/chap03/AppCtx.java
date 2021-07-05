package chap03;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	@Bean
	public MemberDAO memberDAO1() {
		return new MemberDAO();
	}
	@Bean
	@Qualifier("dao")
	public MemberDAO memberDAO2() {
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
