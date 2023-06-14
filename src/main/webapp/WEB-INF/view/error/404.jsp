<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 - 페이지를 찾을 수 없습니다.</title>
</head>
<body>
<c:if test="${requestScope.type.role eq 'ADMIN'}">
</c:if>
<h1> 해당 페이지를 찾을 수 없습니다. </h1>

<form>

</form>
</body>
</html>