package controller.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.Role;
import model.service.BoardService;
import paging.Paging;

public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String p = req.getParameter("p") == null ? "1" : req.getParameter("p");
		p = p.isEmpty() ? "1" : p;

		String c = req.getParameter("c");
		// 파라미터 추출

//		String c = req.getParameter("c") == null ? "1" : req.getParameter("c");
//		c = c.isEmpty() ? "1" : c;
//		int pageLimit = Integer.parseInt(c);

		Cookie boardCookie = null;
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("boardListLimit")) {
				boardCookie = cookie;
			}
		}
		// 쿠키 추출

		int pageLimit = 10;
		if (boardCookie != null) {
			pageLimit = Integer.parseInt(boardCookie.getValue());
		}
		// 쿠키 있으면 쿠키 사용

		if (c != null) {
			if (!c.isEmpty()) {
				pageLimit = Integer.parseInt(c);
				boardCookie = new Cookie("boardListLimit", c);
				boardCookie.setMaxAge(60 * 60 * 24 * 5);
				resp.addCookie(boardCookie);
			}
		}

		int pageNumber = Integer.parseInt(p);

		BoardService service = new BoardService();
		Paging data = service.getPage(pageNumber, pageLimit);
		System.out.println(data.getData());
//		if (((List<BoardDTO>) data.getData()).size() == 0) {
//			System.out.println("리스트 데이터 없는거 확인용");
//		}
		req.setAttribute("paging", data);

		if (((Role) session.getAttribute("role")).isAdmin()) {
			req.getRequestDispatcher("/WEB-INF/view/admin/board/main.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/view/board/main.jsp").forward(req, resp);
		}
	}
}