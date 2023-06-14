package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BookmarkDTO;
import model.dto.UserDTO;
import model.service.BookmarkService;
import paging.Paging;

public class BookmarkController extends HttpServlet {

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

		HttpSession session = req.getSession();
		// 유저 아이디 알려주기 위한 세션 생성

		UserDTO userData = (UserDTO) session.getAttribute("user");
		// 세션을 통해 유저 아이디 알려주기. 현재 로그인 된 UserDTO에 user값 받아오기

		BookmarkService service = new BookmarkService();

		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(userData.getUserId());
		// BookmarkController 부분이므로 BookmarkDTO에 userId 멤버변수를 생성한다.
		// 이후 UserDTO에 접속되어 있는 "user"를 받아오기 위해
		// dto.setUserId(userData.getUserId()); 코드를 입력한다.

		Paging data = service.getPage(dto, Integer.parseInt(p), cnt);
		// getAll()에 dto 담아서 유저 아이디 알려주기
		// 페이징 작업을 위해 getPage()로 변경.

		req.setAttribute("paging", data);

		req.getRequestDispatcher("/WEB-INF/view/Bookmark.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 접속된 계정으로 즐겨찾기 추가하기 위한 작업

		UserDTO userData = (UserDTO) session.getAttribute("user");
		// 접속된 계정으로 즐겨찾기 추가하기 위한 작업

		String url = req.getParameter("url");
		String name = req.getParameter("name");

		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(userData.getUserId());
		// 접속된 계정으로 즐겨찾기 추가하기 위한 작업

		dto.setUrl(url);
		dto.setName(name);

		BookmarkService service = new BookmarkService();
		boolean result = service.add(dto);

		if (result) {
			resp.sendRedirect(req.getContextPath() + "/bookmark");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
			// 저장이 되지않았을 경우 에러페이지를 생성할 것
		}

	}

}
