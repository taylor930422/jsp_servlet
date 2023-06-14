package controller.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import model.dto.Role;
import model.dto.UserDTO;
import model.service.BoardService;

public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = req.getServletContext();
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");

		String id = req.getParameter("id");

		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));

		BoardService service = new BoardService();
		BoardDTO data = service.getData(dto);

//		File f = new File("");
//		f.delete();

		List<BoardImageDTO> imageList = service.getImages(dto);

		if (((Role) session.getAttribute("role")).isAdmin()) {
			boolean result = service.delete(data);
			for (BoardImageDTO image : imageList) {
				String realPath = sc.getRealPath(image.getPath());
				File f = new File(realPath + image.getUuid());
				f.delete();
			}

			resp.sendRedirect(req.getContextPath() + "/board");
		} else {
			if (data.getWriter().equals(user.getUserId())) {
				boolean result = service.delete(data);

				for (BoardImageDTO image : imageList) {
					String realPath = sc.getRealPath(image.getPath());
					File f = new File(realPath + image.getName());
					f.delete();
				}
				if (result) {
					resp.sendRedirect(req.getContextPath() + "/board");
				} else {
					resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + data.getId());
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/error");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String[] ids = req.getParameterValues("chk_id");
//		System.out.println(Arrays.toString(ids));

		if (((Role) session.getAttribute("role")).isAdmin()) {
			BoardService service = new BoardService();
			List<Integer> arrId = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				arrId.add(Integer.parseInt(ids[i]));
			}
			service.delete(arrId);
		}
		resp.sendRedirect(req.getContextPath() + "/board");
	}
}
