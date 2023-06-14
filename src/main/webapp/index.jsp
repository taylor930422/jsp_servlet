<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈 화면</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp"%>
<style>
body {
	background-size: cover;
}
</style>
</head>
<body background="static/img/NewYork.jpg">
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp"%>
	</div>
	<div class="d-flex justify-content-center">
		<div class="mt-5">
			 <h1 class="">Hello, This is Taylor's JSP/Servlet</h1>
			<%-- <c:if test="${sessionScope.role.type eq 'ADMIN' }">
				<p class="float-end">${sessionScope.role.type }계정으로 접속 중</p>
			</c:if>
			<c:if test="${sessionScope.role.type eq 'STAFF' }">
				<p class="float-end">${sessionScope.role.type }계정으로 접속 중</p>
			</c:if> --%>
			<p class="float-end">${sessionScope.role.type } 계정으로 접속 중</p>
		</div>
	</div>
</body>
</html>