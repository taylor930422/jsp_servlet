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

//@WebServlet("/board/nrec")
public class NRecController extends HttpServlet {

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
			service.incNRecCnt(dto);
			nRecHistory.add(id);
		} else if (nRecHistory.contains(id)) {
			service.decreNRecCnt(dto);
			nRecHistory.remove(id);
		}
		session.setAttribute("boardNRecCntHistory", nRecHistory);

		resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + id);
	}
}