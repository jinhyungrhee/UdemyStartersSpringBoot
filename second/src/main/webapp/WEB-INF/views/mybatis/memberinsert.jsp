<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원가입폼</h1>

<!-- name은 dto 필드와 동일하게 설정! -->
<!-- html form(파라미터이름) = MemberDTO(프로퍼티이름) = MEMBER테이블(컬럼이름) 저장 : 이름이 같으면 자동으로 들어감! -->
<form action="<%=request.getContextPath() %>/memberinsert" 
enctype="multipart/form-data" method=post>  
아이디<input type=text name="id" value=""><br>
암호<input type=password name="pw"><br>
이름<input type=text name="name"><br>
폰<input type=text name="phone" pattern="010[0-9]{4}[0-9]{4}"><br>
이메일<input type=email name="email"><br>
주소<input type=text name="address"><br>
이미지<input type="file" name="imagefile" name="imagefile"><br>
가입일자동전송<input type=hidden name="indate" value="<%=new java.util.Date()%>" > 
<input type=submit value="회원가입">
</form>


</body>
</html>