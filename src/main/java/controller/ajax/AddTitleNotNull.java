package controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajax/addTitleNotNull")
public class AddTitleNotNull extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String notNull = req.getParameter("notNull");
		String ccNotNull = req.getParameter("ccNotNull");

		System.out.println(notNull + "   <   notNull");
		System.out.println(ccNotNull + "   <   ccNotNull");

		String chgcc = ccNotNull.replace("<p><br></p>", "");
		System.out.println(chgcc + "   <   chgcc");

		if (chgcc.equals("")) {
			System.out.println("3");
		}

		JsonFactory jf = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jf.createGenerator(sw);

		jg.writeStartObject();
//		if (notNull == "" || notNull.equals("")) {
//			jg.writeStringField("tmsg", "Fail");
//		} else {
//			jg.writeStringField("tmsg", "OK");
//		}
//		if (chgcc.equals("") || chgcc == "") {
//			jg.writeStringField("cmsg", "Fail");
//		} else {
//			jg.writeStringField("cmsg", "OK");
//		}

		if ((notNull == "" || notNull.equals("")) && (chgcc.equals("") || chgcc == "")) {
			jg.writeStringField("emsg", "Fail");
		}

		if (!(notNull == "" || notNull.equals("")) && (chgcc.equals("") || chgcc == "")) {
			jg.writeStringField("cmsg", "Fail");
		}

		if ((notNull == "" || notNull.equals("")) && !(chgcc.equals("") || chgcc == "")) {
			jg.writeStringField("tmsg", "Fail");
		}

		if (!(notNull == "" || notNull.equals("")) && !(chgcc.equals("") || chgcc == "")) {
			jg.writeStringField("msg", "OK");
		}
		jg.writeEndObject();
		jg.close();

		PrintWriter out = resp.getWriter();
		out.print(sw.toString());
		out.flush();

	}

}
