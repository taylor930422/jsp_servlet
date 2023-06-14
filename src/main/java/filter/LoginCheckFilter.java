package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession session = request.getSession();
		if ((boolean) session.getAttribute("login")) { // false
			chain.doFilter(req, resp);
		} else {
			if (request.getRequestURI().contains("ajax")) {
				response.getWriter().print("{\"redirect\": \"" + request.getContextPath() + "/login\"}");
				response.getWriter().flush();
			} else {
				response.sendRedirect(request.getContextPath() + "/login");

			}
		}

	}
}
