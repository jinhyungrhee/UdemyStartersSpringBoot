<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<!-- 두 가지 submit에 대해서 동적으로 처리 필요 -->
<form action="">
<table border="2">
<tr><td>번호</td><td><input type=text value="${board.seq}" readonly></td></tr>
<tr><td>제목</td><td><input type=text value="${board.title}" ></td></tr>
<tr><td>내용</td><td><textarea cols=50 rows=5 >${board.contents}</textarea></td></tr>
<tr><td>작성자</td><td><input type=text value="${board.writer}" readonly></td>
</tr>
<td>조회수</td><td><input type=text value="${board.viewcount}" readonly></td>
<tr>
<td>작성시간</td><td><input type=text value="${board.writingtime}" readonly></td>
</tr>
<tr>
<td><input type="submit" value="수정버튼"></td><td><input type="submit" value="삭제버튼"></td>
</tr>
</table>
</form>

</body>
</html>