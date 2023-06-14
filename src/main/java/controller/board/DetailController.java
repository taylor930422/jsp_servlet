package controller.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import model.service.BoardService;

public class DetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		List<Integer> history = (List<Integer>) session.getAttribute("boardViewHistory");

		String id = req.getParameter("id");

		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));

		BoardService service = new BoardService();

//		if (((Role) session.getAttribute("role")).isGUEST()) {
//			resp.sendRedirect(req.getContextPath() + "/login");
//			return;
//		}
		if (!history.contains(Integer.valueOf(id))) {
			service.incViewCnt(dto);
			history.add(Integer.valueOf(id));
			session.setAttribute("boardViewHistory", history);

		}

		BoardDTO data = service.getData(dto); // id에 해당하는 데이터를 가져오는 작업
		List<BoardImageDTO> images = service.getImages(data);
		if (data != null) {
			req.setAttribute("data", data);
			req.setAttribute("images", images);
			req.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(req, resp);

		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
		// System.out.println(data);
		// DB에서 data 잘 받아오는지 확인하는 작업

//		boolean res = service.incViewCnt(dto);
//		if (res) {
//			data.setViewCnt(data.getViewCnt() + 1);
//		}
		// jsp에 data와 req, resp를 넘기는 작업
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));

		BoardService service = new BoardService();
		BoardDTO data = service.getData(dto);

		resp.getWriter().print("{\"context\":\" " + data.getContext().replace("\"", "'") + "\"}");
		System.out.println(data);
		resp.getWriter().flush();
	}
}
