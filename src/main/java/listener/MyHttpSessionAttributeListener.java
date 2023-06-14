package listener;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeAdded(event);
		System.out.println("session add: " + event.getName() + " - " + event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeRemoved(event);
		System.out.println("session remove: " + event.getName() + " - " + event.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeReplaced(event);
		System.out.println("session replace: " + event.getName() + " - " + event.getValue());
	}
}
