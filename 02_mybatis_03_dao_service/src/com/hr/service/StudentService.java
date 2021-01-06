package com.hr.service;

import com.hr.domain.Student;

import java.util.List;

/**
 * @author Administrator
 */
public interface StudentService {
    public Student getById(String id);

    public void save(Student S);

    List<Student> getAll();
}
