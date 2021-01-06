package com.hr.test;


import com.hr.domain.Student;
import com.hr.service.StudentService;
import com.hr.service.impl.StudentServiceImpl;
import com.hr.util.ServiceFactory;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {

        //走代理，才能事务提交
        StudentService ss = (StudentService)ServiceFactory.getServiceFactory(new StudentServiceImpl());

/*
        Student s = new Student();
        s.setId("A0005");
        s.setName("hr");
        s.setAge(25);

        //invoke()
        ss.save(s);

*/
        //查询所有记录
        List<Student> lstu = ss.getAll();
        for (Student s1:lstu
             ) {
            System.out.println(s1);
        }
    }
}
