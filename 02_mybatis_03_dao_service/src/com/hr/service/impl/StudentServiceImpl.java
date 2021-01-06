package com.hr.service.impl;

import com.hr.dao.StudentDao;

import com.hr.domain.Student;
import com.hr.service.StudentService;
import com.hr.util.SqlSessionUtil;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    /**
    *dao层写在成员变量上
     **/
    private StudentDao studentDao = SqlSessionUtil.getSqlSession().getMapper(StudentDao.class);

    @Override
    public Student getById(String id) {
        Student s = studentDao.getById(id);
        return  s;
    }

    @Override
    public void save(Student s) {
        studentDao.save(s);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }
}
