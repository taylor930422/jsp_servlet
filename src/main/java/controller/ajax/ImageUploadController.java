package controller.ajax;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class ImageUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = req.getServletContext();

		Part image = req.getPart("imageUpload"); // 파일 객체로 생성된 blob의 네임을 받아온다.
		String location = req.getParameter("location"); // 저장 경로 board

		// 이미지를 /static/img/board/년-월-일/uuid.확장자 형식으로 저장.
		// 저장 경로를 json 형식으로 반환하게 합니다.

		int maxFileSize = Integer.parseInt(sc.getInitParameter("maxFileSize"));
		String permitFileType = sc.getInitParameter("permitFileType");
		String[] permitFileExt = sc.getInitParameter("permitFileExt").split(",");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String path = "/static/img/" + location + "/" + df.format(new Date());
		String realPath = sc.getRealPath(path);
		// E:\workspace.... 실제 경로 + path

		File f = new File(realPath);
		if (!f.exists()) {
			// realPath 경로 있냐 없냐 판단.
			f.mkdirs();
			// 없을 경우 mkdirsS();로 없는 폴더를 생성함.
		}
		if (image.getSize() <= maxFileSize) {
			if (image.getContentType().startsWith(permitFileType)) {
				if (image.getContentType().endsWith(permitFileExt[0])
						|| image.getContentType().endsWith(permitFileExt[1])) {
					String uuid = UUID.randomUUID().toString();
					image.write(String.join("/", realPath, uuid));
					// 서버에 저장
					// String.join("/", "Hello", "Hi") --> "Hello/Hi"
					// String.join("/", "a", "b", "c", "d") --> "a/b/c/d"

					resp.getWriter()
							.print("{\"imageUrl\": \"" + req.getContextPath() + String.join("/", path, uuid) + "\"}");
					resp.getWriter().flush();
					// 제이슨 형식으로 imageUrl(키)속성에 값을 저장 후 ajax 보내주기
					// 보내준 imageUrl 속성은 success 메소드에 의해 실행된다.

				}
			}
		}
	}

//		System.out.println(image.getSubmittedFileName());
//		System.out.println(location);
//
//		resp.getWriter().printf("{\"imageUrl\": \"/static/img/board/test.jpeg\"}");
//		resp.getWriter().flush();

}
