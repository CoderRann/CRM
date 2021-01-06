package com.hr.test;


import com.hr.domain.Student;
import com.hr.service.StudentService;
import com.hr.service.impl.StudentServiceImpl;
import com.hr.util.ServiceFactory;

public class Test1 {
    public static void main(String[] args) {

        //走代理，才能事务提交
        StudentService ss = (StudentService)ServiceFactory.getServiceFactory(new StudentServiceImpl());

        /*
        Student s = new Student();
        s.setId("A00005");
        s.setName("cxk");
        s.setAge(25);

        ss.save(s); //invoke()
        */

        System.out.println(ss.getById("A0001"));

    }
}
