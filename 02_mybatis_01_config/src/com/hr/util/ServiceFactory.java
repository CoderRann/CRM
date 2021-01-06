package com.hr.util;

public class ServiceFactory {
    public static Object getServiceFactory(Object service){

        return  new TransactionInvocationHandler(service).getTarget();
    }
}
