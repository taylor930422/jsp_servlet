package controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.UserDTO;
import model.service.UserService;

//@WebServlet("/ajax/userIdCheck")
public class UserIdCheckController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");

		UserDTO dto = new UserDTO();
		dto.setUserId(userId);

		UserService service = new UserService();
		UserDTO data = service.getData(dto);

//		String json = "{";
//		if (data == null) {
//			json += "\"msg\": \"OK\"";
//		} else {
//			json += "\"msg\": \"Fail\"";
//		}
//		json += "}";

		JsonFactory jf = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jf.createGenerator(sw);

		if (data == null) {
			jg.writeStringField("msg", "OK");
		} else {
			jg.writeStringField("msg", "Fail");
		}
		jg.writeEndObject();
		jg.close();

		PrintWriter out = resp.getWriter();
//		out.println(json);
		out.print(sw.toString());
		out.flush();
	}
}
