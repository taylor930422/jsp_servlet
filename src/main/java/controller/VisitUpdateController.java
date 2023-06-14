package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;

public class VisitUpdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		VisitDTO dto = new VisitDTO();
		dto.setId(Integer.parseInt(id));

		VisitService service = new VisitService();
		VisitDTO data = service.getId(dto);

		req.setAttribute("data", data);
		req.getRequestDispatcher("/WEB-INF/view/visitupdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String nickname = req.getParameter("nickname");
		String context = req.getParameter("context");

		VisitDTO dto = new VisitDTO();
		dto.setId(Integer.parseInt(id));
		dto.setNickname(nickname);
		dto.setContext(context);

		VisitService service = new VisitService();
		boolean result = service.update(dto);

		if (result) {
			resp.sendRedirect("../visit");
		} else {
			resp.sendRedirect("../error");
		}

	}
}
