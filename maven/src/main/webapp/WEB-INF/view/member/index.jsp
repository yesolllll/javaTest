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
<div>
		<c:if test="${empty sessionScope.memberInfo}">
			<input type="button" value="로그인" onclick="location.href='form.do'">
			<input type="button" value="회원등록" onclick="location.href='write.do'">
    	</c:if> 
    	
    	<c:if test="${!empty sessionScope.memberInfo}">
    		<input type="button" value="로그아웃" onclick="location.href='logout.do'">
    		<input type="button" value="마이페이지" onclick="location.href='mypage.do'">
    	</c:if><br>
</div>    	
<c:forEach var="member" items="${list}">
	${member.mno} ${member.mname} ${member.email}<br>
</c:forEach>
<hr>
<%@ page import="chap09.*" %>
<%@ page import="java.util.*" %>
<%List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");%>
<%for(int i=0; i<list.size(); i++){ %>
<%=list.get(i).getMno() %> <%=list.get(i).getMname() %> <%=list.get(i).getEmail() %><br>
<%} %>
</body>
</html>
