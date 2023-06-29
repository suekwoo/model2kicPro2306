<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function passChk(form) {
	//alert("ok")
	if (form.chgpass1.value != form.chgpass2.value){
		alert("변경 비밀번호와 재입력 번호가 다릅니다")
		form.chgpass2.value=""
		form.chgpass2.focus()
		return false;
	} else {
		return true;
	}	
}

</script>
<body>
	<%
	String id = (String) session.getAttribute("id");
	String msg = "로그인이 필요합니다";
	String url = "loginForm.jsp";
	if (id != null && !id.equals("")) {
	%>
	<form class="w3-container" action="memberPassPro.jsp" method="post"
	
	onsubmit="return passChk(this)"
	>
			<p>
			<label>현재 비밀번호</label> 
			<input class="w3-input" type="password" 	name="pass">
		</p>
		<p>
			<label>변경 비밀번호</label> 
			<input class="w3-input" type="password" 	name="chgpass1">
		</p>
		<p>
			<label>변경 비밀번호 재입력</label> 
			<input class="w3-input" type="password" 	name="chgpass2">
		</p><p>
			<input class="w3-input" type="submit" value="비밀번호 변경">
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