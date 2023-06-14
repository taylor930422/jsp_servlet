package controller.ajax;

import jakarta.servlet.http.HttpServlet;

public class LoginCheckController extends HttpServlet {
	/*
	 * @Override protected void doGet(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { String userId =
	 * req.getParameter("userId");
	 * 
	 * UserDTO dto = new UserDTO(); dto.setUserId(userId);
	 * 
	 * UserService service = new UserService(); UserDTO data = service.getData(dto);
	 * 
	 * JsonFactory jf = new JsonFactory(); StringWriter sw = new StringWriter();
	 * JsonGenerator jg = jf.createGenerator(sw);
	 * 
	 * jg.writeStartObject(); if (data.getUserId() == dto.getUserId()) {
	 * jg.writeStringField("idMsg", "OK"); } else if (data.getUserId() !=
	 * dto.getUserId()) { jg.writeStringField("idMsg", "Fail"); }
	 * jg.writeEndObject(); jg.close();
	 * 
	 * PrintWriter out = resp.getWriter(); out.println(sw.toString()); out.flush();
	 * 
	 * }
	 */
}
