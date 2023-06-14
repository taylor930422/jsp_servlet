package model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.BookmarkDTO;

public class BookmarkDAO {

	private SqlSession session = OracleConnection.getSqlSession();

	public int insert(BookmarkDTO dto) {
		int count = session.insert("bookmarkMapper.insert", dto);
		return count;
	}

	public int getId() {
		int id = session.selectOne("bookmarkMapper.getId");
		return id;
	}

	public List<BookmarkDTO> selectPage(Map<String, Object> map) {
		List<BookmarkDTO> data = session.selectList("bookmarkMapper.selectPage", map);
		return data;
	}

	public int totalRowCount(BookmarkDTO dto) {
		int count = session.selectOne("bookmarkMapper.totalRowCount", dto);
		return count;
	}

	public List<BookmarkDTO> selectAll(BookmarkDTO dto) {
		List<BookmarkDTO> data = session.selectList("bookmarkMapper.selectAll", dto);
		return data;
	}

	public BookmarkDTO selectId(BookmarkDTO dto) {
		BookmarkDTO data = session.selectOne("bookmarkMapper.selectId", dto);
		return data;
	}

	public int update(BookmarkDTO dto) {
		int count = session.update("bookmarkMapper.update", dto);
		return count;
	}

	public int delete(BookmarkDTO dto) {
		int count = session.delete("bookmarkMapper.delete", dto);
		return count;
	}

	public void commit() {
		session.commit();
	}

	public void rollback() {
		session.commit();
	}

	public void close() {
		session.commit();
	}

}
