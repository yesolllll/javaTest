package chap08;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //DB에서 mybatis를 사용하기 위한 객체로 sqlSessionTemplate 사용하기 위해 
public class MemberDAO {

	@Autowired //빈에 등록되어있는 객체타입을 찾아 주입
		SqlSessionTemplate sqlSessionTemplate;
	
	public List<MemberVO> selectList(){
		return sqlSessionTemplate.selectList("member.selectList"); //마이바티스가 호출해서 리턴 ☆외우기☆(xml위치에 있는 namespace.id , parameterType(1개일땐 기본자료형, 2개 이상은 객체))
	}
	
	public MemberVO login(MemberVO vo) {
		return sqlSessionTemplate.selectOne("member.login", vo);
	}
	public MemberVO selectOne(int mno) {
		return sqlSessionTemplate.selectOne("member.selectOne", mno);
	}
	public int update(MemberVO vo) {
		return sqlSessionTemplate.update("member.update", vo);
	}
}
