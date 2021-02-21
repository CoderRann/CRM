package com.hr.crm.settings.web.controller;

import com.hr.crm.settings.domain.User;
import com.hr.crm.settings.service.UserService;
import com.hr.crm.settings.service.impl.UserServiceImpl;
import com.hr.crm.utils.MD5Util;
import com.hr.crm.utils.PrintJson;
import com.hr.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到用户控制器");

        String path = request.getServletPath();

        if ("/settings/user/login.do".equals(path)){
            login(request,response);
        }else if ("".equals(path)){

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到登录操作");

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        //将密码转为MD5密文
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收ip地址
        String ip = request.getRemoteAddr();
        System.out.println("-------------ip"+ip);

        //使用代理类形态的接口对象
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());



        try {
            User user = us.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);

            //如果程序执行到此处，说明业务层没有异常
            /*
            {"success": true,false,}
             */
            //1.手动拼接字符串
            //String str = "{\"success\":true}";
            //2.使用工具
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();
            //登录失败
            /*
            {"success": true,false,
                    "msg" : "那里错了"}
             */
            String msg = e.getMessage();
            /*
            作为controller，需要为ajax请求提供多项信息
            1.map
            2.创建V0
             */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }

    }

}























































































