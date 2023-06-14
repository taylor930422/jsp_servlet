package listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

//@WebListener
public class MyServletRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequestListener.super.requestInitialized(sre);
		System.out.println("Request 객체가 생성되었습니다.");
		// HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		// 우리가 사용하던 HttpServletRequest보다 상위 객체이므로 다운 캐스팅 해주어야한다.
		// request(사용자 요청) 발생시 추가적인 작업이 필요할때 사용한다.
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletRequestListener.super.requestDestroyed(sre);
		System.out.println("Request 객체가 소멸되었습니다.");
		// request(사용자 요청) 발생시 추가적인 작업이 소멸될때 사용한다.
	}
}
