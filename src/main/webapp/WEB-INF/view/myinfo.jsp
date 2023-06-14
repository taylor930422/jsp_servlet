<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 수정</title>
<div>
	<%@ include file="/WEB-INF/view/module/bootstrap.jsp"%>
</div>
</head>
<body>
	<script type="text/javascript">
		function previewImage(img) {
			console.log(img);
			console.log(img.files);
			console.log(URL.createObjectURL(img.files[0]));

			var preview = document.getElementById("preview");
			preview.src = URL.createObjectURL(img.files[0]);
		}
	</script>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp"%>
	</div>
	<div class="container-sm">
		<div class="row">
			<div class="d-flex justify-content-center mt-3">
				<h1>개인 정보 수정</h1>
			</div>
			<div class="d-flex justify-content-center">
				<div class="col-4 ms-5">
					<c:url var="myinfoUrl" value="/myinfo" />
					<form class="mt-5 me-5" action="${myinfoUrl }/myinfoUploadImage"
						method="post" enctype="multipart/form-data">
						<!-- 
						파일은 문자열이 아니다. 바이너리(바이트 데이터)다.
						파일 전송시 바이트 단위로 쪼개서 보내야하는데
						enctype이 꼭 필요하다. 
						-->
						<div class="border rounded-4" >
							<c:url var="imgUrl" value="${sessionScope.user.pImg }" /> 
							<img src="${imgUrl }" id="preview" class="img-fluid" alt="">
							<!-- 2. 선택한 이미지는 previewImage 코드를 거쳐서 id값을 통해 img에 보여지게 됨 -->
						</div>
						<div class="input-group mt-4">
							<input class="form-control" type="file" name="imageFile" 
								onchange="previewImage(this);"
								accept="image/png, image/jpeg, image/jfif, image/jpg" multiple>
								<!-- 1. 사용자에게 사용자가 선택한 이미지를 단순하게 보여주는 것 : preview -->
								<!-- multiple -> 여러개 사진을 올릴 수 있다.-->
							<button class="btn btn-primary" type="submit">전송</button>
							<!-- <label class="input-group-text">이미지 선택</label> -->
						</div>
					</form>
				</div>
				<div class="col-3">
					<form class="mt-5" action="${myinfoUrl }" method="post">
						<div class="mb-2">
							<label>아이디</label> <input class="form-control" type="text"
								value="${sessionScope.user.userId }" disabled>
						</div>
						<div class="mb-2">
							<label>현재 패스워드</label> <input class="form-control"
								type="password" name="password">
						</div>
						<div class="mb-2">
							<label>변경 할 패스워드</label> <input class="form-control"
								type="password" name="changePassword">
						</div>
						<div>
							<label>이메일 주소</label> <input class="form-control" type="email"
								name="email" value="${sessionScope.user.email }">
						</div>
						<div>
							<button class="btn btn-primary opacity-75 float-end mt-2"
								type="submit">변경하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>