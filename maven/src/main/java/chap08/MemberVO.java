package chap08;
 
//스프링이 관리할 대상이 아니므로 어노테이션 작성 안해도 됨
public class MemberVO {
	private int mno;
	private String mname;
	private String email;
	private String pwd;
	private String checkEmail;
	
	public String getCheckEmail() {
		return checkEmail;
	}
	public void setCheckEmail(String checkEmail) {
		this.checkEmail = checkEmail;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
