package model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.VisitDTO;

public class VisitDAO {

	private SqlSession session = OracleConnection.getSqlSession();

	public int insert(VisitDTO dto) {
		int count = session.insert("visitMapper.insert", dto);
		return count;
	}

	public int getId() {
		int id = session.selectOne("visitMapper.getId");
		return id;
	}

	public List<VisitDTO> selectAll() {
		List<VisitDTO> data = session.selectList("visitMapper.selectAll");
		return data;
	}

	public List<VisitDTO> selectPage(Map<String, Integer> map) {
		List<VisitDTO> data = session.selectList("visitMapper.selectPage", map);
		return data;
	}

	public int totalRowCount() {
		int count = session.selectOne("visitMapper.totalRowCount");
		return count;
	}

	public VisitDTO selectId(VisitDTO dto) {
		VisitDTO data = session.selectOne("visitMapper.selectId", dto);
		return data;
	}

	public int update(VisitDTO dto) {
		int count = session.update("visitMapper.update", dto);
		return count;
	}

	public void commit() {
		session.commit();
	}

	public void rollback() {
		session.rollback();
	}

	public void close() {
		session.close();
	}

	public int delete(VisitDTO dto) {
		int count = session.delete("visitMapper.delete", dto);
		return count;
	}

}
