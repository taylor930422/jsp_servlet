package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.UserDTO;
import model.service.UserService;

public class JoinController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setPassword(password);
		dto.setEmail(email);

		UserService service = new UserService();
		int result = service.add(dto);

		switch (result) {
		case -1:
			req.setAttribute("error", "동일한 아이디가 사용중 입니다.");
			req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
			break;
		case 0:
			req.setAttribute("error", "알 수 없는 문제로 가입에 실패하였습니다.");
			req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
			break;
		case 1:
			resp.sendRedirect(req.getContextPath() + "/login");
			break;
		case 2:
			req.setAttribute("error", "아이디와 비밀번호에는 공백을 사용할 수 없습니다.");
			req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
			break;
		}

	}

}