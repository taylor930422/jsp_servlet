package controller.ajax;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.service.BoardService;

public class RecommendController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		List<Integer> recHistory = (List<Integer>) session.getAttribute("boardRecCntHistory");
		List<Integer> nRecHistory = (List<Integer>) session.getAttribute("boardNRecCntHistory");

		Integer id = Integer.parseInt(req.getParameter("id"));
		String type = req.getParameter("type");

		BoardDTO dto = new BoardDTO();
		dto.setId(id.intValue());

		BoardService service = new BoardService();

		JsonFactory jf = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jf.createGenerator(sw);

		jg.writeStartObject();
		if (type.equals("rec")) {
			if (!recHistory.contains(id) && !nRecHistory.contains(id)) {
				service.incRecCnt(dto);
				recHistory.add(id);
			} else if (recHistory.contains(id)) {
				service.decreRecCnt(dto);
				recHistory.remove(id);
			}
			session.setAttribute("boardRecCntHistory", recHistory);
			BoardDTO data = service.getData(dto);
			jg.writeStringField("type", "success");
			jg.writeNumberField("count", data.getRecCnt());

		} else if (type.equals("norec")) {
			if (!recHistory.contains(id) && !nRecHistory.contains(id)) {
				service.incNRecCnt(dto);
				nRecHistory.add(id);
			} else if (nRecHistory.contains(id)) {
				service.decreNRecCnt(dto);
				nRecHistory.remove(id);
			}
			session.setAttribute("boardNRecCntHistory", nRecHistory);
			BoardDTO data = service.getData(dto);
			jg.writeStringField("type", "success");
			jg.writeNumberField("count", data.getnRecCnt());
		} else {
			jg.writeStringField("type", "error");
			jg.writeStringField("msg", "잘못된 타입 입니다.");
		}
		jg.writeEndObject();
		jg.close();

		resp.getWriter().print(sw.toString());
		resp.getWriter().flush();
	}
}
