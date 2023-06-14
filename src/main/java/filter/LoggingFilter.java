package filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebFilter("/*")
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a KK:mm:ss.SSS");
		String path = request.getRequestURI();
		String method = request.getMethod();
		String query = request.getQueryString();

		System.out.printf("[%s] - %s: %s", df.format(new Date()), method, path);
		if (query != null) {
			System.out.print("?" + query);
		}
		System.out.println();

//		System.out.println(df.format(new java.util.Date()));
//		System.out.println("path : " + path);
//		System.out.println("method : " + method);
//		if (query != null) {
//			System.out.println("query : " + query);
//		}

		chain.doFilter(req, resp);

		int code = response.getStatus();
		String redirect = response.getHeader("Location");

		System.out.printf("[%s] - %s: ", df.format(new Date()), code);

		switch (code / 100) {
		case 2:
			System.out.print("정상");
			break;
		case 3:
			System.out.printf("리다이렉트(%s)", redirect);
			break;
		case 4:
			System.out.print("요청 오류");
			break;
		case 5:
			System.out.print("서버 오류");
			break;
		}
		System.out.println();

//		System.out.println(df.format(new Date()));
//		System.out.println("code : " + code);
//		System.out.println("redirect URL : " + redirect);

	}

}
