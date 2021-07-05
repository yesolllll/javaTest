<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	[첫번째 방법!]<br>
	이름: ${param.name }<br>
	이메일: ${param.email }<br>
		<br>
	[두번째 방법! - HttpServletRequest]<br>
	이름: ${name1 }<br>
	이메일: ${email1 }<br>
		<br>
	[세번째 방법! - @RequestParam]<br>
	이름: ${name2 }<br>
	이메일: ${email2 }<br>
	번호: ${no }<br>
		<br>
	[네번째 방법! - 커맨드 객체 MemberVO 생성후 vo객체를 가져옴]<br>
	이름: ${vo.name }<br>
	이메일: ${vo.email }<br>
	번호: ${vo.no }<br>
		<br>	
	[다섯번째 방법! - 커맨드 객체 (request set 없이) MemberVO 생성후 vo객체를 가져옴]<br>
	이름: ${memberVO.name }<br>
	이메일: ${memberVO.email }<br>
	번호: ${memberVO.no }<br>
		<br>
	[request에서 get]<br>
	아이디: ${id}<br>
	
	
</body>
</html>