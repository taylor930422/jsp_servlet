package controller.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.service.BoardService;

//@WebServlet("/board/rec")
public class RecController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<Integer> recHistory = (List<Integer>) session.getAttribute("boardRecCntHistory");
		List<Integer> nRecHistory = (List<Integer>) session.getAttribute("boardNRecCntHistory");

		Integer id = Integer.parseInt(req.getParameter("id"));

		BoardDTO dto = new BoardDTO();
		dto.setId(id.intValue());

		BoardService service = new BoardService();
		if (!recHistory.contains(id) && !nRecHistory.contains(id)) {
			service.incRecCnt(dto);
			recHistory.add(id);
		} else if (recHistory.contains(id)) {
			service.decreRecCnt(dto);
			recHistory.remove(id);
		}
		session.setAttribute("boardRecCntHistory", recHistory);

		resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + id);
	}
}