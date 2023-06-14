package model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.BoardDAO;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import paging.Paging;

public class BoardService {

	public Paging getPage(int pageNumber, int pageLimit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageNumber - 1) * pageLimit + 1);
		map.put("end", pageNumber * pageLimit);
		// 한 페이지에 10개 목록 출력하면
		// 1번 페이지 1 ~ 10, 2번 페이지 11 ~ 20, 3번 페이지는 21 ~ 30을
		// 만들어주기 위한 방법

		BoardDAO dao = new BoardDAO();
		List<BoardDTO> data = dao.selectPage(map);
		int totalRowCount = dao.totalRowCount();
		int lastPageNumber = (totalRowCount / pageLimit) + (totalRowCount % pageLimit == 0 ? 0 : 1);
		dao.close();
		// 마지막 페이지를 알아내기 위한 작업
		// 전체 레코드 수 55개, 한 페이지 목록 수 10개인 경우 ( 55 / 10 ) + ( 55 % 5 == 0 ? 0 : 1);
		// 나머지가 0이면 0, 1이면 나머지를 위해 한 페이지를 더 출력하는 작업

		Paging paging = new Paging(data, pageNumber, lastPageNumber, pageLimit, 5);
		return paging;
	}

	public BoardDTO getData(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.selectData(dto);
		dao.close();
		return data;
	}

	public boolean incViewCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateViewCnt(dto);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean add(BoardDTO dto, List<BoardImageDTO> boardImageList) {
		BoardDAO dao = new BoardDAO();
		int id = dao.selectNextSeq();
		dto.setId(id);
		int count = dao.insert(dto);
		if (count == 1) {
			if (boardImageList != null) {
				for (BoardImageDTO image : boardImageList) {
					image.setBoardId(id);
					dao.insertImage(image);
					// id값을 서비스에서 받아서 image와 같이 추가한다.
				}
			}
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean updateData(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.update(dto);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean delete(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		dao.deleteImages(dto);
		int count = dao.delete(dto);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public int delete(List<Integer> arrId) {
		BoardDAO dao = new BoardDAO();
		int count = dao.delete(arrId);
		if (count <= arrId.size()) {
			dao.commit();
			dao.close();
			return count;
		}
		dao.rollback();
		dao.close();
		return -1;
	}

	public boolean incRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateRecCnt(dto);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean incNRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateNRecCnt(dto);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean decreRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateDecreRecCnt(dto);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean decreNRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateDecreNRecCnt(dto);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public List<BoardImageDTO> getImages(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		List<BoardImageDTO> data = dao.selectImages(dto);
		dao.close();
		return data;
	}

}
