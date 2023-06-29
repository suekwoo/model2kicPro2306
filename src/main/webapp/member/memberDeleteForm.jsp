<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = (String) session.getAttribute("id");
	String msg = "로그인이 필요합니다";
	String url = "loginForm.jsp";
	if (id != null && !id.equals("")) {
	%>
	<form class="w3-container" action="memberDeletePro.jsp" method="post">
		<p>
			<label>비밀번호</label> <input class="w3-input" type="password"
				name="pass">
		</p>
		<p>
			<input class="w3-input" type="submit" value="확인">
		</p>
	</form>
	<%	} else {	%>
	<script>
		alert("로그인을 하세요")
		location.href = "loginForm.jsp";
	</script>
	<%	}	%>
</body>
</html>