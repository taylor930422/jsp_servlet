package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("사용자가 get 요청을 보냈습니다.");
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String num = req.getParameter("num");
		String n = req.getParameter("num");
		Integer number = 0;
		if (n != null && !n.isEmpty()) {
			number = Integer.parseInt(n);
		}
		req.setAttribute("number", number);
//		System.out.println("클라이언트가 보낸 데이터 : " + num);
		req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
	}
}
