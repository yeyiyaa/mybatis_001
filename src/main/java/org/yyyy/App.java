package org.yyyy;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
       //使用文件流读取核心配置文件sqlMapConfig.xml
        InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        //
        List<Student> students = sqlSession.selectList("zar.getAll");
        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }
}
