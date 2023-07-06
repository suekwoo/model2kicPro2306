<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="w3-container">
		<table class="w3-table-all">
			<tr>
				<td>글번호</td>
				<td>${board.num}</td>
				<td>조회수</td>
				<td>${board.readcnt}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.name}</td>
				<td>작성일</td>
				<td>${board.regdate}</td>
			</tr>
			<tr>
				<td>이미지</td>
				<td colspan="3"><img
					src="${pageContext.request.contextPath}/view/board/images/${board.file1}" />
				</td>
			</tr>
			<tr>
				<td>글제목</td>
				<td colspan="3">${board.subject}</td>
			</tr>
			<tr   style="border-bottom: double 3px grey;">
				<td>글내용</td>
				<td colspan="3">${board.content}</td>
			</tr  >
			
			<tr>
				<td colspan="3" class="w3-center"><textarea rows="1"
						class="w3-input w3-border" onkeyup="enterkey('${board.num}')"
						cols="50" name="content" id="comment"></textarea></td>
				<td><input type="button" value="입력(enter 입력)"
					onclick="commentPro('${board.num}')"></td>
			</tr>



		</table>

	</div>
</body>
</html>