package controller.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.dto.UserDTO;
import model.service.BoardService;

public class UpdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");

		String id = req.getParameter("id");

		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));

		BoardService service = new BoardService();
		BoardDTO data = service.getData(dto);

		if (data.getWriter().equals(user.getUserId())) {
			req.setAttribute("data", data);
			req.getRequestDispatcher("/WEB-INF/view/board/update.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
		// 접속한 세션의 유저와 작성자가 같을때만 URL 접근권한 부여
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");

		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String context = req.getParameter("context");

		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));

		BoardService service = new BoardService();
		BoardDTO data = service.getData(dto);

		if (data.getWriter().equals(user.getUserId())) {
			data.setTitle(title);
			data.setContext(context);
			// 업데이트된 내용으로 바꾸는 작업
			boolean result = service.updateData(data);
			if (result) {
				resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + data.getId());
			} else {
				resp.sendRedirect("/WEB-INF/view/board/update.jsp");
			}

		} else {
			resp.sendRedirect(req.getContextPath() + "/error");

		}

	}

}
