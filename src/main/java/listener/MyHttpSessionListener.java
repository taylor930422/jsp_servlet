package listener;

import java.util.ArrayList;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import model.dto.Role;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		System.out.println("세션이 생성되었습니다.");
		HttpSession session = se.getSession();
		session.setAttribute("login", false);
		session.setAttribute("role", new Role("GUEST"));
		session.setAttribute("boardViewHistory", new ArrayList<Integer>());
		session.setAttribute("boardRecCntHistory", new ArrayList<Integer>());
		session.setAttribute("boardNRecCntHistory", new ArrayList<Integer>());
		System.out.println("로그인 안됨");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
		System.out.println("세션이 소멸되었습니다.");
	}
}