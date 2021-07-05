package chap04;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = {"chap04"})
public class MemberDAO {
	private static long nextId= 0;
	private Map<String, Member> map = new HashMap<String, Member>();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	public void select() {		//main2
		for(String key : map.keySet()) {
			Member m =map.get(key);
				System.out.println( "["+m.getId()+"]\r"
									+m.getEmail()+"\r"
									+m.getName()+"\r"
									+m.getPassword()+"\r"
									+m.getRegisterDateTime());
			
		}
	}
	
	public Map<String, Member> selectList(){ 		//main
		return map;
	}
}
