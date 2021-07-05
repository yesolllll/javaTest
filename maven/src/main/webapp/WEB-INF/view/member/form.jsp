<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login.do" method="post">
<table border="1">
	<tr>
		<td>email</td>
		<td>
			<input type="text" name="email" value="${memberVO.email }">
			<input type="checkbox" name="checkEmail" value="check" <c:if test="${memberVO.checkEmail=='check'}">checked</c:if>>이메일 저장
		</td>
		
	</tr>
	<tr>
		<td>password</td>
		<td><input type="password" name="pwd"></td>
		<%//커맨드 객체를 사용하기 위해 name을 memberVO와 동일하게 사용 %>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="submit" value="login♡">
		</td>
	</tr>

</table>
</form>
</body>
</html>