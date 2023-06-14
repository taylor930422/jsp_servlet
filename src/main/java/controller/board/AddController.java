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

public class AddController extends HttpServlet {

	String referer = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/board/add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 계정 정보를 불러오기 위한 세션
//		ServletContext sc = req.getServletContext();

		referer = req.getHeader("referer");
//		System.out.println(referer);

		String title = req.getParameter("title");
		String context = req.getParameter("context");

		String newContext = context.replace("<p><br></p>", "");

		if ((title == "" || title.equals("")) && (newContext.equals("") || newContext == "")) {
			resp.sendRedirect(req.getContextPath() + "/board/add");
			return;
		}

		if (!(title == "" || title.equals("")) && (newContext.equals("") || newContext == "")) {
			resp.sendRedirect(req.getContextPath() + "/board/add");
			return;
		}

		if ((title == "" || title.equals("")) && !(newContext.equals("") || newContext == "")) {
			resp.sendRedirect(req.getContextPath() + "/board/add");
			return;
		}
//		if (!(title == "" || title.equals("")) && !(newContext.equals("") || newContext == "")) {
//	}

		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContext(context);
		dto.setWriter(((UserDTO) session.getAttribute("user")).getUserId());
		dto.setBtype("B");

		if (req.getParameter("notice") != null) {
			if (req.getParameter("notice").equals("yes")) {
				dto.setBtype("N");
			}
		}

//		int maxFileSize = Integer.parseInt(sc.getInitParameter("maxFileSize"));
//		String permitFileType = sc.getInitParameter("permitFileType");
//		String[] permitFileExt = sc.getInitParameter("permitFileExt").split(",");
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//		String path = "/static/img/board/" + df.format(new Date()) + "/";
//		String realPath = sc.getRealPath(path);
//		File f = new File(realPath);
//		if (!f.exists()) {
//			// realPath 경로 있냐 없냐 판단.
//			f.mkdirs();
//			// 없을 경우 mkdirsS();로 없는 폴더를 생성함.
//		}
//
////		UUID uuid = UUID.randomUUID();
////		uuid.toString();
//
//		Collection<Part> parts = req.getParts();
//		// 하나의 인풋에 여러개 올리는 것 = .getParts();
//		List<BoardImageDTO> boardImageList = new ArrayList<BoardImageDTO>();
//		for (Part part : parts) {
//			if (part.getName().equals("imageUpload")) {
//				if (part.getSize() <= maxFileSize) {
//					if (part.getContentType().startsWith(permitFileType)) {
//						if (part.getContentType().endsWith(permitFileExt[0])
//								|| part.getContentType().endsWith(permitFileExt[1])) {
//							String uuid = UUID.randomUUID().toString();
//							part.write(String.join("/", realPath, part.getSubmittedFileName()));
//							// 1. 파일 추가한것을
//							// realpath : 서버의 절대 경로
//							// path : DB에서 사용할 경로
//							BoardImageDTO boardImageDTO = new BoardImageDTO();
//							boardImageDTO.setPath(path);
//							boardImageDTO.setName(part.getSubmittedFileName());
//							boardImageDTO.setUuid(uuid);
//							boardImageList.add(boardImageDTO);
//							// 2. 에드 시키고
//						}
//					}
//				}
//			}
//		}

//		if(req.getParameter("notice") == null) {
//			if(req.getParameter("notice").equals("no")) {
//				dto.setBtype("B");
//			}
//		}

		BoardService service = new BoardService();
		boolean result = service.add(dto, null);
		// 3. 서비스에 넘긴다
		if (result) {
			resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + dto.getId());
		} else {
			req.getRequestDispatcher("/WEB-INF/view/board/add.jsp").forward(req, resp);
		}
	}
}
