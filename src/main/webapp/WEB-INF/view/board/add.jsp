<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가 페이지</title>
<div>
	<%@ include file="/WEB-INF/view/module/bootstrap.jsp"%>
</div>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		/* function imageValid(element) {
			if (element.files.length > 3) {
				alert('이미지는 3개까지만 선택할 수 있습니다.');
				element.value = "";
			}
		} */
		
	</script>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp"%>
	</div>
	<div>
		<c:url var="boardAddUrl" value="/board/add" />
		<form class="container-sm" action="${boardAddUrl }" method="post"
			onsubmit="return sendForm(this);">
			<!-- enctype="multipart/form-data"> form의 enctype도 필요 없어짐-->
			<div>
				<h1>글 작성</h1>
			</div>
			<div class="mb-2">
				<label>제목</label> <input class="col-auto" type="text" name="title">
			</div>
			<div>
				<!-- <textarea name="context"></textarea> -->
				<div id="editor"></div>
				<textarea style="display: none;" name="context"></textarea>
			</div>
			<c:if test="${sessionScope.role.type eq 'ADMIN' }">
				<div>
					<label>공지사항</label> <input type="checkbox" name="notice"
						value="yes">
				</div>
			</c:if>
			<div>
				<button onclick="addTitleNotNull();">저장</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
			<div>
				<!-- <label>이미지 업로드</label> <input type="file"
					onchange="imageValid(this);" name="imageUpload" accept="image/*"
					multiple> -->
				<!-- imageValid(this); 사용자가 선택한 파일이 3개 이하인지 확인하는 것 -->
				<!-- editor에 이미지 업로드 기능 넣을거라서 필요 없어짐 -->
			</div>
		</form>
	</div>
	<script type="text/javascript">
	
	const editor = new toastui.Editor({
		el : document.querySelector('#editor'),
		hight : "250px",
		hooks : {
			"addImageBlobHook" : function(blob, callback) {
				// addImageBlobHook : 이대로 사용하면 됨. 정해진 것
				// blob: 사용자가 선택한 이미지 파일
				// callback: 파일이 업로드 된 후 에디터에 표시할 이미지 주소를 전달하기 위한 콜백함수
	 			let formData = new FormData();
				formData.append("imageUpload", blob);
				formData.append("location", "board")
				// 폼 데이터라는 객체가 있다. 키와 값이다.
				
				
				
				<c:url var="imageUploadUrl" value="/ajax/imageUpload" />
					$.ajax({
						url: "${imageUploadUrl }",
						type: "post",
						data: formData,
						dataType: "json",
						enctype: "multipart/form-data",
						processData: false,
						contentType: false,
						success: function(data) {
							callback(data.imageUrl);
						}
					});
				}
			}
			})
	
	function sendForm(form) {
		// Toast UI Editor에 작성된 글 가져와서 전송할 폼에 넣기.
		form.context.innerText = editor.getHTML();
		/* form.submit();
		return false; */
		return true;
	}
	
	<c:url var="addTitleNotNullUrl" value="/ajax/addTitleNotNull" />
		function addTitleNotNull() {
			var form = document.forms[0]; 
			var title = form.title.value;
			var context = editor.getHTML();
			var link = '/web01/board/add';
			
			$.ajax({
				type: "get",
				url: "${addTitleNotNullUrl}",
				data: {
					notNull: title,
					ccNotNull: context
				},
				dataType: "json",
				success: function(data) {
					if(data.tmsg == "Fail"){
						location.href = link;
						alert("제목 없다");
					}
					if(data.cmsg == "Fail"){
						location.href = link;
						alert("내용 없다");
					}
					if(data.emsg == "Fail"){
						location.href = link;
						alert("둘다 없다");
					}
					if(data.msg == "OK"){
						alert("글작성ㅊㅋㅊㅋ");
					}
				}
			});	
		}
	
		
		
		
	</script>
</body>
</html>