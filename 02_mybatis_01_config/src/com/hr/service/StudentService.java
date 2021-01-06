package com.hr.service;

import com.hr.domain.Student;

public interface StudentService {
    public Student getById(String id);

    public void save(Student S);
}
