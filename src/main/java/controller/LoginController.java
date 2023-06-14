package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.Role;
import model.dto.UserDTO;
import model.service.UserService;

public class LoginController extends HttpServlet {

	String referer = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

// 	    쿠키 테스트
//      Cookie[] cookies = req.getCookies();
//		for (Cookie c : cookies) {
//			System.out.println(c.getName() + " | " + c.getValue());
//
//		}
//
//		Cookie cookie = new Cookie("name", "value");
//		cookie.setMaxAge(60 * 60 * 24);
//
//		resp.addCookie(cookie);
//		// 클라이언트의 쿠키에 쿠키를 저장
//		--------------------------------------------------------
//		HttpSession session = req.getSession();
		// 세션이 없는 경우 새로 생성 후 반환해준다. 즉, 항상 세션은 "있다"라는 의미다.
		// (false) : 세션이 있으면 session에 담아주고, 없으면 null이다.
//		session.setAttribute("세션명1", 객체);
//		session.setAttribute("세션명2", 객체);
//		session.setAttribute("세션명3", 객체);
//		session.getAttribute("세션명1");
//		session.removeAttribute("세션명1");
//		session.invalidate(); // 세션에서 사용되는 객체들을 바로 해제 (세션 자체를 소멸)
		// 로그인 -> 세션 정보 저장
		// 로그아웃 -> 세션 삭제, 이때 invalidate()를 사용한다.

		HttpSession session = req.getSession();

		if ((boolean) session.getAttribute("login")) {
			resp.sendRedirect(req.getContextPath());
			return;
		}

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("remember")) {
				req.setAttribute("remember", c.getValue());
			}
		}
		referer = req.getHeader("referer");
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");

//		System.out.println(remember);
//		체크가 되어있는 상태에서는 on이 출력되고,
//	    체크가 되어있지 않은 상태에서는 null 값이 출력된다.

		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setPassword(password);

		UserService service = new UserService();
		UserDTO userData = service.login(dto);
		// null -> 로그인 x / not null -> 로그인 o

		Cookie cookie = new Cookie("remember", userId);

		if (userData != null) {
			HttpSession session = req.getSession();
			Role role = service.getRole(userData);
//			session.setAttribute("login", true);
			session.setAttribute("role", role);
			session.setAttribute("user", userData);
			// 여기서 userData가 DTO다. 바인딩 되면 로그인 된다.
			if (remember != null) {
				cookie.setMaxAge(60 * 60 * 24 * 5);
			} else {
				cookie.setMaxAge(0);
			}
			resp.addCookie(cookie);
//			resp.sendRedirect(req.getContextPath());
			resp.sendRedirect(referer);

		} else {
			req.setAttribute("error", "로그인에 실패하였습니다. 다시 시도해주세요.");
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		}
	}
}
