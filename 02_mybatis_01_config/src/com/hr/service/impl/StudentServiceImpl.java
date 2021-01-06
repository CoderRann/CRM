package com.hr.service.impl;

import com.hr.dao.StudentDao;
import com.hr.dao.impl.StudentDaoImpl;
import com.hr.domain.Student;
import com.hr.service.StudentService;

public class StudentServiceImpl implements StudentService {
    //dao层写在成员变量上
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Student getById(String id) {
        Student s = studentDao.getById(id);
        return  s;
    }

    @Override
    public void save(Student s) {
        studentDao.save(s);
    }

}
