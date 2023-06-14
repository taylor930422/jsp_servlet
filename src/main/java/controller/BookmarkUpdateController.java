package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BookmarkDTO;
import model.dto.UserDTO;
import model.service.BookmarkService;

public class BookmarkUpdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		UserDTO userData = (UserDTO) session.getAttribute("user");
		// 로그인 된 계정을 확인하기 위한 작업

		String id = req.getParameter("id");

		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(userData.getUserId());// 로그인 된 계정을 확인하기 위한 작업

		BookmarkService service = new BookmarkService();
		BookmarkDTO data = service.getId(dto);

		if (data == null) {
			resp.sendRedirect(req.getContextPath() + "/error");
			// 사용자가 가지고 있지 않은 즐겨찾기를 찾을때 error 페이지를 발생시키도록 하는 작업.
		} else {
			req.setAttribute("data", data);
			req.getRequestDispatcher("/WEB-INF/view/bookmarkupdate.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// MyHttpSessionListener에서 설정한 코드가 아래의 ("login") == null 비교를 대신함
//		if (session.getAttribute("login") == null) {
//			resp.sendRedirect(req.getContextPath() + "/login");
//			return;
//		}

		UserDTO userData = (UserDTO) session.getAttribute("user");
		// 로그인 된 계정을 확인하기 위한 작업

		String id = req.getParameter("id");
		String url = req.getParameter("url");
		String name = req.getParameter("name");

		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(userData.getUserId());
		// 로그인 된 계정으 확인하기 위한 작업
		dto.setId(Integer.parseInt(id));
		dto.setUrl(url);
		dto.setName(name);

		BookmarkService service = new BookmarkService();
		boolean result = service.update(dto);

		if (result) {
			resp.sendRedirect("../bookmark");
		} else {
			resp.sendRedirect("../error");
		}

	}

}
