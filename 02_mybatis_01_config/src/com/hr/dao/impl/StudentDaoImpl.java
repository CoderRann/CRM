package com.hr.dao.impl;

import com.hr.dao.StudentDao;
import com.hr.domain.Student;
import com.hr.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student getById(String id) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        Student s = session.selectOne("test1.getById",id);
        return  s;
    }

    @Override
    public void save(Student s) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        session.insert("test1.save",s);

        //在业务层提交事务!!!session.commit();
    }
}
