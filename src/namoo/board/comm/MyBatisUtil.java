package namoo.board.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	//
		SqlSession sqlsession = null;
		
	public <T> T getMapper(Class<T> paramClass){
		//
		try{
		InputStream inputStream =
				Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		sqlsession = sessionFactory.openSession();
		return sqlsession.getMapper(paramClass);
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void commit(){
		sqlsession.commit();
	}
	
	public void closeSqlSession(){
		sqlsession.close();
	}

}
