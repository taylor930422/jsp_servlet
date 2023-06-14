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

public class BookmarkDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		UserDTO userData = (UserDTO) session.getAttribute("user");

		String id = req.getParameter("id");

		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(userData.getUserId());

		BookmarkService service = new BookmarkService();
		boolean result = service.delete(dto);

		if (result) {
			resp.sendRedirect(req.getContextPath() + "/bookmark");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
	}

}
