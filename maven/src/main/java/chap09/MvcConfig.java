package chap09;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages= {"chap09"})
@EnableWebMvc
@EnableTransactionManagement
public class MvcConfig implements WebMvcConfigurer {
	
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
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
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
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
		return sqlSessionTemplate;
	}
	
	
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor(); 
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(loginInterceptor()).addPathPatterns("/member/mypage.do");
		//excludePathPatterns()
		//관리자: .addPathPatterns("/admin/**").excludePathPatterns("/admin/index.do")  == .addPathPatterns(포함하고 싶은 경로).excludePathPatterns(제외하고싶은 경로)
		// -> 로그인 페이지를 제외한 모든 관리자페이지에서 로그인이 되었는지 확인을 하는 방법
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
		conf.setLocations(new ClassPathResource("db.properties"));
		return conf;
	}
	@Bean
	public PlatformTransactionManager txmanager() {
		DataSourceTransactionManager ptm = new DataSourceTransactionManager();
		ptm.setDataSource(dataSource());
		return ptm;
	}
	
}
