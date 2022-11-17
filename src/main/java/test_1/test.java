package test_1;

import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yyyy.Users;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class test {
    SqlSession sqlSession;
    UserMapper uMapper ;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 获取session
     * @throws IOException
     */
    @Before
    public void openSqlSession() throws IOException {
        //读取核心配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂对象
        SqlSessionFactory factory  = new SqlSessionFactoryBuilder().build(in);
        //取出session
        sqlSession=factory.openSession();
        uMapper=sqlSession.getMapper(UserMapper.class);;
    }

    /**
     * 释放资源
     */
    @After
    public void closeSession(){
        sqlSession.close();
    }
    @Test
    public void testGetAll(){
        List<Users> list = uMapper.getAll();
        list.forEach(x-> System.out.println(x));
    }
    @Test
    public void testUpdata() throws ParseException {
        Date date=simpleDateFormat.parse("2022-01-01");
        Users u= new Users(2,"haha",date,"2","清远财贸");
        int update = uMapper.update(u);
        System.out.println(update);
        sqlSession.commit();
    }
    @Test
    public void testgetOne(){
        Users user = uMapper.getById(2);
        System.out.println(user);
    }
    @Test
    public void testgetByName(){
        List<Users>list = uMapper.getByName("张");
        list.forEach(users -> System.out.println(users));
    }
    @Test
    public void testAddOne() throws ParseException {
        Date date=simpleDateFormat.parse("2000-03-21");
        Users users=new Users(8,"符文",date,"1","广东阳江");
        int i = uMapper.addOneUser(users);
        sqlSession.commit();
    }
    @Test
    public  void testDelOneUser(){
        int i = uMapper.delOneUser(1);
        sqlSession.commit();
    }
    @Test
    public  void testgetByNameOrAddress(){
        List<Users> list = uMapper.getByNameOrAddress("address", "财");
        List<Users> list2 = uMapper.getByNameOrAddress("username", "小");
        list.forEach(x-> System.out.println(x));
        System.out.println("--------------------------------------");
        list2.forEach(x-> System.out.println(x));
    }







}
