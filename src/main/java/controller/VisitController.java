//package controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import model.dto.VisitDTO;
//import model.service.VisitService;
//
///**
// * 방명록을 작성할 수 있는 기능을 제공하기 위한 Servlet
// */
//
//// request.setAttribute()
//// session.setAttribute()
//// System.out.println(req.getServletContext().getAttribute("hello"));
//// 모두 동일한 동작형태이다.
//public class VisitController extends HttpServlet {
//
//	/**
//	 * 사용자가 방명록 페이지를 요청하면 방명록을 작성할 수 있는 폼을 담은 JSP 페이지를 실행하여 제공한다 JSP 페이지는 *
//	 * /WEB-INF/view-visit.jsp이다.
//	 */
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String p = req.getParameter("p");
//
//		// System.out.println(req.getServletContext().getAttribute("hello"));
//		VisitService service = new VisitService();
//		List<VisitDTO> data = service.getPage(Integer.parseInt(p));
//
//		req.setAttribute("data", "Hello");
//		req.setAttribute("data", data);
//		req.getRequestDispatcher("/WEB-INF/view/visit.jsp").forward(req, resp);
//		req.removeAttribute("data");
////		ServletRequestAttribute에 대한 속성값 test용.		
//
//	}
//
//	/**
//	 * 사용자가 방명록을 작성 후 저장 요청을 하면 해당 데이터를 저장하기 위한 처리를 수행한다.
//	 */
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		String context = req.getParameter("context");
//		String nickname = req.getParameter("nickname");
////		System.out.println(context);
//
//		VisitDTO dto = new VisitDTO();
//		dto.setContext(context);
//		dto.setNickname(nickname);
//		// 데이터가 하나가 아니라 여러개일때 dto에 데이터를 저장하여
//		// 여러개인 데이터를 캡슐화하여 사용할 수 있도록 하는 것이다.
//
//		VisitService service = new VisitService();
//		boolean result = service.add(dto);
//
//		if (result) {
//			resp.sendRedirect("./visit");
//			// visit으로 재요청
//			// 서버에서 응답할때 사용자가 재접속할 수 있는 주소를 담아서 보내주는 것이다.
//		} else {
//			resp.sendRedirect("./error");
//		}
//	}
//}

package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;
import paging.Paging;

/**
 * 방명록을 작성할 수 있는 기능을 제공하기 위한 Servlet 맵핑된 URL 주소는 /visit 이다.
 */
public class VisitController extends HttpServlet {

	/**
	 * 사용자가 방명록 페이지를 요청하면 방명록을 작성할 수 있는 폼을 담은 JSP 페이지를 실행하여 제공한다. JSP 페이지는
	 * /WEB-INF/view/visit.jsp 이다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");

		if (p == null) {
			p = "1";
		} else {
			if (p.isEmpty()) {
				p = "1";
			}
		}

		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("cnt")) {
				cookie = c;
			}
		}

		int cnt = 10;
		if (cookie != null) {
			if (req.getParameter("c") != null) {
				if (!req.getParameter("c").isEmpty()) {
					cnt = Integer.parseInt(req.getParameter("c"));
					cookie = new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60 * 60 * 24 * 5);
					resp.addCookie(cookie);
				}
			} else {
				cnt = Integer.parseInt(cookie.getValue());
			}
		} else {
			if (req.getParameter("c") != null) {
				if (!req.getParameter("c").isEmpty()) {
					cnt = Integer.parseInt(req.getParameter("c"));
					cookie = new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60 * 60 * 24 * 5);
					resp.addCookie(cookie);
				}
			}
		}

		VisitService service = new VisitService();
		Paging data = service.getPage(Integer.parseInt(p), cnt);
//		int totalRow = service.totalRow();
//		int lastPageNumber = (totalRow / cnt) + (totalRow % cnt == 0 ? 0 : 1);
//		List<Integer> pageList = new ArrayList<Integer>();
//		for(int i = 1; i <= lastPageNumber; i++) {
//			pageList.add(i);
//		}

		req.setAttribute("paging", data);
		req.getRequestDispatcher("/WEB-INF/view/visit.jsp").forward(req, resp);
	}

	/**
	 * 사용자가 방명록을 작성 후 저장 요청을 하면 해당 데이터를 저장하기 위한 처리를 수행한다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = req.getParameter("context");
		String nickname = req.getParameter("nickname");

		VisitDTO dto = new VisitDTO();
		dto.setContext(context);
		dto.setNickname(nickname);

		VisitService service = new VisitService();
		boolean result = service.add(dto);

		if (result) {
			resp.sendRedirect("./visit");
		} else {
			System.out.println("추가 안됨");
		}

	}
}
