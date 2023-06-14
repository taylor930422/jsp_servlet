package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.UserDTO;
import model.service.UserService;

public class MyinfoController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/view/myinfo.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		UserDTO userData = (UserDTO) session.getAttribute("user");
		// 로그인 컨트롤러에 저장된(로그인된) user 정보를 세션에 담은 것.

		String password = req.getParameter("password");
		String changePassword = req.getParameter("changePassword");
		String email = req.getParameter("email");

		UserDTO dto = new UserDTO();
		dto.setPassword(changePassword);
		dto.setEmail(email);

		UserService service = new UserService();
		boolean result = service.update(userData, dto, password);

		if (result) {
			resp.sendRedirect(req.getContextPath() + "/logout");
		} else {
			req.setAttribute("error", "패스워드를 다시 확인하세요.");
			req.getRequestDispatcher("/WEB-INF/view/myinfo.jsp").forward(req, resp);
		}
	}
}
