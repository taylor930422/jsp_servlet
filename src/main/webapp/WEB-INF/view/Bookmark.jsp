<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet"
	href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript"
	src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
</head>
<style>
.a {
text-decoration: none;
}
</style>

<body>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp"%>
	</div>
	<div class="d-flex justify-content-center">
		<c:url var="bookmarkUrl" value="/bookmark" />
		<form action="${bookmarkUrl }" method="post">
			<h2 class="my-2">즐겨찾기 추가</h2>
			<div>
				<input class="form-control" type="text" name="url" placeholder="URL">
			</div>
			<div>
				<input class="form-control" type="text" name="name"
					placeholder="NAME">
			</div>
			<div>
				<button class="btn btn-primary opacity-75 float-end my-2"
					type="submit">저장</button>
			</div>
		</form>
	</div>
	<div class="d-flex justify-content-center">
		<div class="me-4">
		<h2>목록</h2>
		</div>
		<div class="ms-4">
		<form action="${bookmarkUrl }">
			<select class="form-select-sm ms-2 my-2" name="c" onchange="submit();">
				<option value="5"
					${requestScope.paging.pageLimit eq 5 ? "selected" : "" }>5개</option>
				<option value="10"
					${requestScope.paging.pageLimit eq 10 ? "selected" : "" }>10개</option>
				<option value="15"
					${requestScope.paging.pageLimit eq 15 ? "selected" : "" }>15개</option>
				<option value="20"
					${requestScope.paging.pageLimit eq 20 ? "selected" : "" }>20개</option>
				<option value="25"
					${requestScope.paging.pageLimit eq 25 ? "selected" : "" }>25개</option>
			</select>
		</form>
		</div>
		<br>
	</div>
	<div class="d-flex justify-content-center">
		<ul class="list-group">
			<c:forEach var="d" items="${requestScope.paging.data }">
				<c:url var="bookmarkUpdateUrl" value="/bookmark/update">
					<c:param name="id" value="${d.id }" />
				</c:url>
				<c:set var="formId" value="deleteForm${d.id }" />
				<li class="list-group-item"><a class="a" href="${d.url }">${d.name }</a>

					<button class="btn btn-primary btn-sm ms-1 opacity-75 float-end" type="submit" form="${formId }">삭제</button>
					
					<button class="btn btn-primary btn-sm ms-3 opacity-75 float-end" type="button"
						onclick="location.href='${bookmarkUpdateUrl}'">수정</button>


					<form id="${formId }" action="${bookmarkUrl }/delete" method="post">

						<input type="hidden" name="id" value="${d.id }">

					</form></li>
			</c:forEach>
		</ul>
	</div>
	<c:set var="pagingUrl" value="${bookmarkUrl }" />
	<div class="mt-2">
		<%@ include file="/WEB-INF/view/module/paging.jsp"%>
	</div>
</body>
</html>