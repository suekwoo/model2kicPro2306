<%@page import="member.Member"%>
<%@page import="member.MemberDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include  file="head.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<%  String id = (String) session.getAttribute("id");
	String msg = "접근 불가 자료 입니다";
	String url = "loginForm.jsp";
	if (id != null && id.equals("admin")) {  
	List<Member>  li = new MemberDao().memberList();  	
	%> 
<div class="w3-container">
<table class="w3-table-all " style="color:#000">
<tbody><tr class="w3-grey">
  <th>id</th>  <th>비밀번호</th>  <th>이름</th>
  <th>성별</th>   <th>전화번호</th>  <th>이메일</th>   <th>사진명</th>
</tr>
</tbody><tbody>
<% for (Member m : li) { %>
<tr>
<td><%=m.getId() %></td>
<td><%=m.getPass() %></td>
<td><%=m.getName() %></td>
<td><%=m.getGender() %></td>
<td><%=m.getTel() %></td>
<td><%=m.getEmail() %></td>
<td><%=m.getPicture() %></td>
</tr>
<% } %>
</tbody></table></div>
<%	} else {	%>
	<script>
		alert("<%=msg%>")
		location.href = "loginForm.jsp";
	</script>
	<%	}	%>
</body>
</html>