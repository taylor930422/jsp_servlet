package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

//@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		System.out.println("컨텍스트 객체가 생성되었습니다.");
		ServletContext context = sce.getServletContext();
		context.setAttribute("Hello", "안녕하세요");
		// 서버가 시작될때 동작해야할 로직을 입력
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
		System.out.println("컨텍스트 객체가 소멸되었습니다.");
		// 서버가 종료될때 동작해야할 로직을 입력
	}

}
