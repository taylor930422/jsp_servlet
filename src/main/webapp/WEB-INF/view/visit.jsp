<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<div>
	<%@ include file="/WEB-INF/view/module/bootstrap.jsp"%>
</div>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp"%>
	</div>

	<c:url var="visitUrl" value="/visit" />

	<div class="d-flex justify-content-center mt-2">
		<h1>방명록</h1>
		<form action="${visitUrl }">
			<select name="c" onchange="submit();"
				class="form-select-sm mt-3 ms-2">
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
	<div class="d-flex justify-content-center mt-2">
		<form action="${visitUrl }" method="post">
			<div>
				<input class="form-control" type="text" name="nickname"
					placeholder="NICKNAME">
			</div>
			<br>
			<div class="input-group">
				<textarea class="form-control" rows="3" cols="21" name="context"
					placeholder="CONTEXT"></textarea>
			</div>
			<div class="float-end mt-2" >
				<button class="btn btn-primary opacity-75">submit</button>
			</div>
		</form>
	</div>
	<br>
	<div class="d-flex justify-content-center">
		<ul class="list-group">
			<c:forEach var="d" items="${requestScope.paging.data}">
				<c:url var="visitUpdateUrl" value="/visit/update">
					<c:param name="id" value="${d.id}" />
				</c:url>
				<c:set var="formId" value="deleteForm${d.id}" />
				<li class="list-group-item">
				<div id="viewer">
				</div>
				${d.nickname}|${d.context}	
					<div class="float-end">
						<c:choose>
							<c:when test="${sessionScope.role.type eq 'ADMIN' }">
								<button class="btn btn-primary btn-sm ms-3 opacity-75" type="button"
									onclick="location.href='${visitUpdateUrl}'">수정</button>
								<button class="btn btn-primary btn-sm ms-1 opacity-75 " type="submit" form="${formId}">삭제</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-secondary btn-sm ms-3 opacity-75" type="button" disabled>수정</button>
								<button class="btn btn-secondary btn-sm ms-1 opacity-75" type="button" disabled>삭제</button>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
				<form id="${formId}" action="${visitUrl}/delete" method="post">
					<input type="hidden" name="id" value="${d.id}">
				</form>
			</c:forEach>
		</ul>
	</div>
	<c:set var="pagingUrl" value="${visitUrl }" />
	<div class="mt-2">
		<%@ include file="/WEB-INF/view/module/paging.jsp"%>
	</div>
	
	<script type="text/javascript">
		var viewer;
		window.onload = function() {
			<c:url var="visitUrl" value="/visit" />
			$.ajax({
				url: "${visitUrl}",
			   data: {
				   id: ${requestScope.data.id }
			   },
			   type: "post",
			   dataType: "json",
			   success: function(data) {
				  viewer = new toastui.Editor.factory({
					   el: document.querySelector("#viewer"),
					   viewer: true,
				   	   initialValue: data.context
				   });
			   }
			});
		}
	</script>
</body>
</html>