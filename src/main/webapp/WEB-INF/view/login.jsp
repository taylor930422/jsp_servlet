<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<div>
	<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</div>
	<script
            src="https://code.jquery.com/jquery-3.6.3.js"
            integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
            crossorigin="anonymous"></script>
</head>
<body>
<c:url var="loginCheck" value="/ajax/loginCheck" />
<!-- <script type="text/javascript">


	function check(userId) {
	$.ajax({
		type: "get",
		 url: "${loginCheck}",
		data: { 
			userId : userId.value, 
		},
		dataType: "json",
		success: function(data) {
			console.log(data);
			if(data.idMsg == "OK"){
				console.log("로그인 성공");
				alert("로그인 성공");
			} else if(data.idMsg == "Fail") {
				console.log("로그인 실패");
				alert("로그인 실패");
			}
		},
	})
	}
	function validCheck() {
		if(valid) {
			return true;
		} else{
			alert("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
			return false;	
		}
	} 


	
	
	/* function validCheck() {
		if(valid == 0) {
			return true;
		} else if(valid == -1){
			alert("아이디를 잘못 입력하셨습니다.");
			return false;
		} else if(valid == -2){
			alert("비밀번호를 잘못 입력하셨습니다.");
			return false;
			
		}
	}  */
</script> -->
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<div class="d-flex justify-content-center">
	<h1 class="mt-2">로그인</h1>
	</div>
	<div class="container-sm">
		<div class="row">
		<div class="col-4">
		</div>
			<c:url var="loginUrl" value="/login" />
			<form class="col-4" action="${loginUrl }" method="post">
				<div>
					<label>아이디</label> <input class="form-control" type="text"
						name="userId" value="${cookie.remember.value }">
					<div>
						<c:if test="${not empty requestScope.error }">
							<span style="color: red;">${requestScope.error }</span>
						</c:if>
					</div>
				</div>
				<div>
					<label>패스워드</label> <input class="form-control" type="password"
						name="password">
				</div>
				<div class="my-2">
					<c:choose>
						<c:when test="${empty requestScope.remember }">
							<input type="checkbox" name="remember">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="remember" checked>
						</c:otherwise>
					</c:choose>
					<label>아이디 기억하기</label>
					<button class="btn btn-primary opacity-75 float-end">로그인</button>
				</div>
			</form>
			<div class="col-4">
			</div>
		</div>
	</div>
</body>
</html>