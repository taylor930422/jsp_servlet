package database.connect;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OracleConnection {

	public static SqlSession getSqlSession() {
		SqlSession sess = null;

		try (InputStream is = Resources.getResourceAsStream("resources/mybatis-config.xml")) {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			sess = factory.openSession(false);
			// openSession(false)의 false는 자동커밋 off 상태이다.
		} catch (IOException e) {
			e.printStackTrace();
		}
		// sql세션을 만들어주는 공장이라 SqlSessionFactory
		return sess;
	}
}
