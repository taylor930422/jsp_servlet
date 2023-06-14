<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.a {
	text-decoration: none;
}
</style>
<c:url var="mainUrl" value="/" />
<nav class="navbar navbar-expand-lg bg-primary bg-opacity-50">
	<div class="container-fluid">
		<a class="a text-light" href="${mainUrl }">JSP/Servlet</a>
		<button class="navbar-toggler" type="button">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-between">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="ms-5 nav-link"
					href="${mainUrl }visit">방명록</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${mainUrl }board">게시판</a></li>
				<li class="nav-item"><c:if test="${sessionScope.login }">
						<a class="nav-link" href="${mainUrl }bookmark">즐겨찾기 링크 모음</a>
					</c:if></li>
			</ul>
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${sessionScope.login }">
						<li class="nav-item">
						<a class="nav-link" href="#">${sessionScope.user.userId }님이 접속중</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${mainUrl }logout">로그아웃</a></li>
						<li><a class="nav-link" href="${mainUrl }myinfo">개인정보</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="nav-link" href="${mainUrl }login">로그인</a></li>
						<li><a class="nav-link" href="${mainUrl }join">회원가입</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>