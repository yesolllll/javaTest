package chap08;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages= {"chap08"})
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
		conf.enable();
	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		reg.jsp("/WEB-INF/view/", ".jsp");
	}
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		reg.addViewController("/index.do").setViewName("index");
	} 
	
	@Bean  //빈에 등록 https://addio3305.tistory.com/62 참고
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3307/webdb");
		dataSource.setUsername("webuser");
		dataSource.setPassword("web1234");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		 SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		 ssfb.setDataSource(dataSource());
		 PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		 ssfb.setMapperLocations(resolver.getResources("classpath://mapper/**/*.xml"));
		 return ssfb.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {  
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;  // 실제 dao에서 사용하게될 객체 < 이 메소드를 사용하기 위해 dataSource와 sqlSessionFactory 메소드 생성 >
	}
	
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor(); //->만든 클래스 빈등록 만약 로그인이 되지 않은 상태에서 허용되지 않은 페이지에 들어가려면 메소드에 도달하기도전에 오류가 뜸
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(loginInterceptor()).addPathPatterns("/member/mypage.do");
		//excludePathPatterns()
		//관리자: .addPathPatterns("/admin/**").excludePathPatterns("/admin/index.do")  == .addPathPatterns(포함하고 싶은 경로).excludePathPatterns(제외하고싶은 경로)
		// -> 로그인 페이지를 제외한 모든 관리자페이지에서 로그인이 되었는지 확인을 하는 방법
	}
}
