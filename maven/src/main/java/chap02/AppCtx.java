package chap02;

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
		return new MemberRegisterService(memberDAO());
	}
	
	@Bean
	public ChangePasswordService pwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDAO(memberDAO());
		return pwdSvc;
	}
	@Bean
	public MemberListService listSvc() {
		return new MemberListService(memberDAO());
	}
	
	@Bean
	public MemberInfoService infoSvc() {
		return new MemberInfoService(memberDAO());
	}
}
