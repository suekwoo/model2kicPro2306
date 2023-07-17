package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnection {
	
	private static SqlSessionFactory sqlmap;
	
	
	static {
		Reader reader  = null;
		try {
			reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
			sqlmap = new SqlSessionFactoryBuilder().build(reader);
			System.out.println("config ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	public static SqlSession  getConnection() {
		return sqlmap.openSession();
	}
	public static void  close(SqlSession session) {
		session.commit(); session.close();	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession  sqlsession = getConnection();
		System.out.println(sqlsession);	} 
	}
