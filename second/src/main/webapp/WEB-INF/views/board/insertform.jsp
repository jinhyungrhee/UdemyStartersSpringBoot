<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="insertboard" method="post">
<table border="2">
<tr><td>제목</td><td><input type=text name="title" ></td></tr>
<tr><td>내용</td><td><textarea rows=5 name="contents"></textarea></td></tr>
<tr><td>작성자</td><td><input type=text value="${sessionScope.loginid }" name="writer" readonly></td></tr>
<td>조회수</td><td><input type=text name="viewcount" value="0"></td>
<tr>
<td colspan="2"><input type="submit" value="글 작성"></td>
</tr>
</table>
</form>

</body>
</html>