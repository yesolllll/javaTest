package chap02;

import java.util.HashMap;
import java.util.Map;

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
				System.out.println(m.getEmail()+m.getName()+m.getPassword()+m.getRegisterDateTime());
			
		}
	}
	
	public Map<String, Member> selectList(){ 		//main
		return map;
	}
}
