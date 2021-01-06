package com.hr.dao;

import com.hr.domain.Student;

public interface StudentDao {
    public Student getById(String id);

    public void save(Student S);
}
