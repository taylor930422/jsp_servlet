package model.dto;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import model.service.UserService;

public class UserDTO implements HttpSessionBindingListener {
	// 세션에 데이터를 붙였을때(바인딩되었을때) 추가적인 작업을 바운딩,언바운딩 한다.
	private String userId;
	private String password;
	private String email;
	private String pImg;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", password=" + password + ", email=" + email + ", pImg=" + pImg + "]";
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSessionBindingListener.super.valueBound(event);
		HttpSession session = event.getSession();
		session.setAttribute("login", true);

		UserService service = new UserService();
		Role role = service.getRole((UserDTO) event.getValue());

		if (role == null) {
			session.setAttribute("role", new Role("GUEST"));
		} else {
			session.setAttribute("role", role);
		}
	}
}
//		System.out.println("로그인됨");
//		if (session.getAttribute("role").equals("ADMIN")) {
//			session.setAttribute("role", new Role("ADMIN"));
//			System.out.println("롤 어드민");
//		}
//		System.out.println("session binding : " ]q	+ event.getName() + " - " + event.getValue());

// set일때 bound

//	@Override
//	public void valueUnbound(HttpSessionBindingEvent event) {
//		HttpSession session = event.getSession();
//		session.setAttribute("login", false);
//		HttpSessionBindingListener.super.valueUnbound(event);
////		System.out.println("session unbinding : " + event.getName() + " - " + event.getValue());
//	}
// remove일때 unbound
