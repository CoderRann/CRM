package com.hr.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {

    //target:业务层实现类对象
    private  Object target;

    public TransactionInvocationHandler(Object target){
        this.target = target;
    }

    //代理类的业务方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession session = null;
        Object obj = null;

        try {
            session = SqlSessionUtil.getSqlSession();

            //处理业务逻辑
            //业务层实现类方法
            obj = method.invoke(target,args);

            //处理业务逻辑完毕，提交事务

            session.commit();
        }catch (Exception e){
            session.rollback();
            e.printStackTrace();
        }finally {
            SqlSessionUtil.closeSession(session);
        }
        return obj;
    }

    //取得代理类对象
    public Object getTarget() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
