package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.dto.UserDTO;
import model.service.UserService;

//@WebServlet("/myinfo/uploadImage")
//@MultipartConfig(location = "E:/imageTemp", maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024
//		* 5, fileSizeThreshold = 1024)
public class MyinfoImageUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		String viewPath = "";

		UserDTO user = (UserDTO) session.getAttribute("user");

		Part imageFile = req.getPart("imageFile");
		// req.getParameter(""); == req.getPart(""); 이다.
		int maxFileSize = Integer.parseInt(req.getServletContext().getInitParameter("maxFileSize"));
		String permitFileType = req.getServletContext().getInitParameter("permitFileType");
		String[] permitFileExt = req.getServletContext().getInitParameter("permitFileExt").split(",");
//		web.xml에 전역설정한 값 가져오는 것.
//		imageFile.write(image.file.getSubmiitedFilename());
//		if (imageFile.getSize() <= 5242880) {
//			if (imageFile.getContentType().startsWith("image")) {
//				if (imageFile.getContentType().endsWith("png") || imageFile.getContentType().endsWith("jpeg")) {
		if (imageFile.getSize() <= maxFileSize) {
			if (imageFile.getContentType().startsWith(permitFileType)) {
				// startswith() : 시작 문자열이 지정된 문자와 같은지 boolean으로 반환
				if (imageFile.getContentType().endsWith(permitFileExt[0])
						|| imageFile.getContentType().endsWith(permitFileExt[1])) {
					// endWith() : 끝 문자열이 지정된 문자와 같은지 boolean으로 반환
					// getContentType() : literally Content Type. When the Client send 'request
					// Object' to server
					System.out.println("파일명 : " + imageFile.getSubmittedFileName());
					System.out.println("파일크기 : " + imageFile.getSize());
					System.out.println("파일종류 : " + imageFile.getContentType());

					String path = req.getServletContext().getRealPath("/static/img/profile");
					// System.out.println(path);
					// 매핑되어 있는 실제 location 경로 외 실제 경로를 의미한다.

					// if (imageFile.getContentType().equals("image/png")) {
					// imageFile.delete();
					// } else {
					// }

					imageFile.write(path + "/" + imageFile.getSubmittedFileName());
					// 서버에 경로를 저장되게 하는 작업
					// 지정된 경로에 사용자가 업로드 요청한 파일을 저장한다.
					// 즉 현재 location에 지정된 경로 외 .getRealPath()로 개발자가 새로운 경로를 서버에 지정해준 것이다.

					user.setpImg("/static/img/profile/" + imageFile.getSubmittedFileName());
					UserService service = new UserService();
					service.uploadImage(user);
					resp.sendRedirect(req.getContextPath() + "/myinfo");
					return;
				} else {
					req.setAttribute("errorType", "fileExtError");
//					viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
					System.out.println("이미지 파일은 png 또는 jpeg만 가능합니다.");

					// 별도의 에러페이지 생성하기
				}
			} else {
				req.setAttribute("errorType", "fileTypeError");
				System.out.println("파일은 이미지 파일만 업로드 할 수 있습니다.");
//				viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
				// 별도의 에러페이지 생성하기
			}
		} else {
			req.setAttribute("errorType", "fileSizeError");
			System.out.println("파일 용량 초과");
//			viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
			// 별도의 에러페이지 생성하기
		}
		req.getRequestDispatcher("/WEB-INF/view/error/imageUploadError.jsp").forward(req, resp);
		// 이미지 파일을 서버에 배포해주기 위한 작업
	}
}
