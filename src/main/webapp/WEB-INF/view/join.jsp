<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<div>
		<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
	</div>
	<script
            src="https://code.jquery.com/jquery-3.6.3.js"
            integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
            crossorigin="anonymous"></script>
</head>
<body>
	<c:url var="userIdCheck" value="/ajax/userIdCheck" />
	<script type="text/javascript">
		valid = false;
		function usernameCheck(element) {
			if(element.nextElementSibling === null) {
				var s = document.createElement("span");
				element.after(s);
			} else {
				var s = element.nextElementSibling;
			}
			$.ajax({
				type: "get", url: "${userIdCheck }",
				data: { userId: element.value },
				dataType: "json",
				success: function(data) {
					console.log("응답이 성공적으로 왔습니다.");
					if(data.msg === "OK") {
						s.style = "color: green;";
						s.innerText = "회원 가입이 가능한 아이디 입니다.";
						valid = true;
					} else if(data.msg === "Fail") {
						s.style = "color: red;";
						s.innerText = "회원 가입이 불가능한 아이디 입니다.";
						valid = false;
					} else {
						console.log("알 수 없는 값 입니다.");
						valid = false;
					}
				},
				error: function(data) {
					// 응답이 왔지만 데이터 처리에 대한 문제가 발생 했을 때 동작하는 함수
					console.log("응답이 왔지만 문제가 발생하였습니다.");
					console.log(data);
				},
				complete: function(data) {
					// 응답이 success / error 이든 상관없이 동작이 필요할 때 사용하는 함수
					console.log("서버로 부터 응답이 왔습니다. 성공/실패를 구분하지 않습니다.");
					console.log(data);
				}
			});
		}
		
		function validCheck() {
			if(valid) {
				return true;
			} else {
				alert("아이디 혹은 비밀번호에 공백을 사용할 수 없습니다.");
				return false;
			}
		}
	</script>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<div class="d-flex justify-content-center my-2">
	<h1>회원가입</h1>
	</div>
	<div class="d-flex justify-content-center">
	<c:url var="joinUrl" value="/join" />
	<form action="${joinUrl }" method="post" onsubmit="return validCheck();">
		<div>
			<label>아이디</label> 
			<input class="form-control" type="text" name="userId" onchange="usernameCheck(this);">
			<div>
<%-- 			<c:if test="${not empty requestScope.error }">
				<span style="color: red;">${requestScope.error }</span>
			</c:if> --%>
			</div>
		</div>
		<div class="mt-1">
			<label>패스워드</label> <input class="form-control" type="password" name="password">
			<%-- ${error } --%>
		</div>
		<div class="mt-1">
			<label>email</label> <input class="form-control" type="email" name="email"
				value=${param.email }>
		</div>
		<button class="btn btn-primary opacity-75 float-end mt-2" type="submit">가입</button>
	</form>
	</div>

</body>
</html>