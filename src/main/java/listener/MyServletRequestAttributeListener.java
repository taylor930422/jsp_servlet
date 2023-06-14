package listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.http.HttpServletRequest;

//@WebListener
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		ServletRequestAttributeListener.super.attributeAdded(srae);
		HttpServletRequest req = (HttpServletRequest) srae.getServletRequest();
		System.out.printf("Add: %s - %s - %s\n", req.getRequestURI(), srae.getName(), srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		ServletRequestAttributeListener.super.attributeRemoved(srae);
		HttpServletRequest req = (HttpServletRequest) srae.getServletRequest();
		System.out.printf("Remove: %s - %s - %s\n", req.getRequestURI(), srae.getName(), srae.getValue());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		ServletRequestAttributeListener.super.attributeReplaced(srae);
		HttpServletRequest req = (HttpServletRequest) srae.getServletRequest();
		System.out.printf("Replace%s - %s - %s\n", req.getRequestURI(), srae.getName(), srae.getValue());
		System.out.println("New Data: " + req.getAttribute(srae.getName()));
		// Replaced는 변경된 Old Data 값을 찾아낸다.
	}
}
