<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>

<script>
$(document).ready(function() {
	
	$("#form").on("submit", function(e) {

		if($("#pw").val() != $("#pw2").val()) {
			$("#result").html("<h3>암호가 일치하지 않습니다.</h3>");
			e.preventDefault();	// 전송 방지
		}
	});
	
	
});
</script>

</head>
<body>

<h1>내정보 페이지</h1>

<!-- name은 dto 필드와 동일하게 설정! -->
<!-- html form(파라미터이름) = MemberDTO(프로퍼티이름) = MEMBER테이블(컬럼이름) 저장 : 이름이 같으면 자동으로 들어감! -->
<form id="form" action="<%=request.getContextPath()%>/memberupdate" method=post>  
아이디<input type=text name="id" value="${member.id }" readonly><br>
암호<input type=password name="pw" id="pw"><br>
암호확인<input type=password name="pw2" id="pw2"><br>
이름<input type=text name="name" value="${member.name }" ><br>
폰<input type=text name="phone" pattern="010[0-9]{8}" value="${member.phone }"><br>
이메일<input type=email name="email" value="${member.email }"><br>
주소<input type=text name="address" value="${member.address }"><br>
<input type=submit value="내정보수정">
</form>

<div id="result"></div>

</body>
</html>