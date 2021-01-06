package com.hr.dao;

import com.hr.domain.Student;

import java.util.List;

public interface StudentDao {
    public Student getById(String id);

    public void save(Student S);

    List<Student> getAll();
}
