<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update.do" method="post">
<table border="1">
	<tr>
		<td>name</td>
		<td><input type="text" name="mname" value="${vo.mname }"></td>
	</tr>
	<tr>
		<td>email</td>
		<td><input type="text" name="email" value="${vo.email }"></td>
	</tr>
	<tr>
		<td>password</td>
		<td><input type="password" name="pwd"></td>
		<%//커맨드 객체를 사용하기 위해 name을 memberVO와 동일하게 사용 %>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" value="목록" onclick='location.href="index.do"'>
		<input type="submit" value="수정">
		<input type="hidden" name="mno" value="${vo.mno}">
		</td>
	</tr>

</table>
</form>
</body>
</html>