<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>요청 파라미터 정보 출력하기</title></head><body>
<h2>요청 파라미터 정보</h2>
<%   request.setCharacterEncoding("UTF-8");%>
이름:${param.name }<br>나이:${param.age }<br>
성별:${param.gender==1?"남자":"여자" }<br>
취미:${param.hobby }<br>
출생년도:${param.year }<br>

<h2>요청파라미터에 저장된 모든 값 조회하기 </h2>
<h3>취미 :</h3>
<c:forEach var="v" items="${paramValues.hobby }">
	${v }&nbsp;&nbsp;&nbsp;
</c:forEach><br>
<h2>모든 요청파라미터의 모든 값 조회하기 </h2><table>
  <tr><th>파라미터이름</th><th>파라미터값</th></tr>
  <c:forEach var="v" items="${paramValues }">
  	<tr><td>${v.key }</td>
  		<td>
  		<c:forEach var="p" items="${v.value }">
  			${p }&nbsp;&nbsp;&nbsp;
  		</c:forEach></td></tr>
  </c:forEach>
</table></body></html>