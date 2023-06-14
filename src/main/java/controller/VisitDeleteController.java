package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;

public class VisitDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VisitDTO dto = new VisitDTO();
		String id = req.getParameter("id");
		dto.setId(Integer.parseInt(id));

		VisitService service = new VisitService();
		boolean result = service.delete(dto);

		if (result) {
			resp.sendRedirect("../visit");
		} else {
			resp.sendRedirect("../error");
		}
	}

}
