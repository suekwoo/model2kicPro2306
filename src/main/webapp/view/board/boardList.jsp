<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html><html><head> 
<meta charset="UTF-8">
<title>Insert title here</title>
</head><body>
<div class="w3-container">
<h3  class="w3-center">${boardName}</h3>
<a class="w3-button   w3-right  w3-grey"   
    href="${pageContext.request.contextPath}/board/boardForm" >게시판 입력</a>
<table class="w3-table-all" style="color:#000">
 <tbody> <tr>
  <th>번호</th>
  <th>이름</th>
  <th>제목</th>
   <th>파일</th>
   <th>입력일</th>
  <th>조회수</th> </tr>
  <c:set var="boardNum"  value="${boardNum}"/>
  <c:forEach  var="b"  items="${list }">
  <tr>
   <td>${boardNum}</td>
   <c:set var="boardNum"  value="${boardNum-1}"/>
  <td>${b.name}</td>
  <td>${b.subject}</td>
  <td>${b.file1}</td>
  <td>${b.regdate}</td>
  <td>${b.readcnt}</td>
   </tr>
  
  </c:forEach>
  </tbody>


</table> 
<br></div>
</body></html>