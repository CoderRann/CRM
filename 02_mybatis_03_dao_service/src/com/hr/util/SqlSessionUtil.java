package com.hr.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 封装sqlSession工具包
 */
public class SqlSessionUtil {

    private SqlSessionUtil(){}

    //优先只创建一个SqlSessionFactory
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //使用ThreadLocal防止多线程访问同一个共享变量的时候容易出现的并发问题
    //ThreadLocal基于当前线程，线程销毁后也随之销毁
    private static ThreadLocal<SqlSession> t = new ThreadLocal<SqlSession>();
    //取得SqlSession对象,每次获取的是同一个session，才能保证事务统一操作
    public static SqlSession getSqlSession(){
        SqlSession session = t.get();
        if (session==null){
            session = sqlSessionFactory.openSession();
            t.set(session);
        }
        return session;
    }


    //关闭session
    public static void closeSession(SqlSession session){
        if (session!=null){
            session.close();
            //tomcat自带线程池，浏览器发出请求后，tomcat分配一个线程去处理请求，
            // 结束后线程并没有被销毁，而是回到线程池中
            // 因此必须将该线程中ThreadLocal清除
            t.remove();
        }
    }
}
