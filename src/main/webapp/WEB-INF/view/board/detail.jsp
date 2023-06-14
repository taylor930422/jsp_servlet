<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 페이지</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet"
	href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript"
	src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</head>
<body>
	<c:url var="recommendUrl" value="/ajax/recommend" />
	<script type="text/javascript">
		function recommend(element, id, type) {
			$.ajax({
				type: "get",
				url: "${recommendUrl }",
				data: {
					id: id,
					type: type
				},
				dataType: "json",
				success: function(data) {
					if(data.redirect !== undefined) {
						let message = "추천/비추천은 회원만 할 수 있습니다. 로그인 페이지로 이동합니다.";
						
						if(confirm(message)) {
							location.href = data.redirect;
						}
					} else {
						if(data.type === "success"){
							if(type === "rec") {
								element.innerText = "추천 : " + data.count
							} else if(type === "norec") {
								element.innerText = "비추천 : " + data.count
							}
					} else if(data.type === "error") {
						console.log(msg);
					}
				}
			}
		});
	}

	</script>
	<c:url var="boardUrl" value="/board" />
	<c:url var="loginUrl" value="/login" />
	<c:url var="joinUrl" value="/join" />
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp"%>
	</div>
	<div class="container-sm">
		<div class="d-flex justify-content-center">
			<h1>${requestScope.data.title }</h1>
		</div>
		<div class="d-flex justify-content-center">
			<fmt:formatDate var="createDate" type="both"
				pattern="yyyy년 MM월 dd일 EEEE a KK시 mm분 ss초"
				value="${requestScope.data.createDate }" />
			<fmt:formatDate var="updateDate" type="both"
				pattern="yyyy년 MM월 dd일 EEEE a KK시 mm분 ss초"
				value="${requestScope.data.updateDate }" />
			작성자: ${requestScope.data.writer }<br> 작성일: ${createDate }<br>
			수정일: ${updateDate }<br> 조회수: ${requestScope.data.viewCnt } <br>
			<%-- 추천수: ${requestScope.data.recCnt } <br> 비추천수 :
			${requestScope.data.nRecCnt } <br> --%>
		</div>
		<div class="d-flex justify-content-center">
			<div id="viewer">
			</div>
			</div>
			<%-- <p>${requestScope.data.context }</p> --%>
		<div>
			<ul>
				<c:forEach var="image" items="${requestScope.images }">
					<c:url var="imagePath" value="${image.path }${image.uuid }" />
					<li><a href="${imagePath }" download="${image.name}">${image.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div>
			<%-- <button type="button" class="btn btn-primary" onclick="location.href='${boardUrl }/rec?id=${requestScope.data.id }'">
			추천 ${requestScope.data.recCnt }</button>
		<button type="button" class="btn btn-danger" onclick="location.href='${boardUrl }/nrec?id=${requestScope.data.id }'">
			비추천 ${requestScope.data.nRecCnt }</button> --%>
			<button type="button" class="btn btn-primary"
				onclick="recommend(this, ${requestScope.data.id }, 'rec');">추천
				: ${requestScope.data.recCnt }</button>
			<button type="button" class="btn btn-danger"
				onclick="recommend(this, ${requestScope.data.id }, 'norec');">비추천
				: ${requestScope.data.nRecCnt }</button>
		</div>
		<div>
			<c:choose>
				<c:when test="${sessionScope.role.type eq 'ADMIN' }">
					<!-- 관리자 o -->
					<button onclick="location.href='${boardUrl }'">목록</button>
					<button
						onclick="location.href='${boardUrl}/update?id=${requestScope.data.id}'">수정</button>
					<button
						onclick="location.href='${boardUrl}/delete?id=${requestScope.data.id}'">삭제</button>
				</c:when>
				<c:when
					test="${sessionScope.user.userId eq requestScope.data.writer }">
					<!-- 동일 o -->
					<button onclick="location.href='${boardUrl }'">목록</button>
					<button
						onclick="location.href='${boardUrl}/update?id=${requestScope.data.id}'">수정</button>
					<button
						onclick="location.href='${boardUrl}/delete?id=${requestScope.data.id}'">삭제</button>
				</c:when>
				<c:when test="${sessionScope.user.userId eq null }">
					<!-- 게스트 o -->
					<button onclick="location.href='${boardUrl }'">목록</button>
					<button onclick="location.href='${loginUrl }'">로그인</button>
					<button onclick="location.href='${joinUrl }'">회원가입</button>
				</c:when>
				<c:otherwise>
					<c:if
						test="${sessionScope.user.userId ne requestScope.data.writer }">
						<!-- 비동일 o -->
						<button onclick="location.href='${boardUrl }'">목록</button>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript">
		var viewer;
		window.onload = function() {
			<c:url var="boardDetailUrl" value="/board/detail" />
			$.ajax({
				url: "${boardDetailUrl}",
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