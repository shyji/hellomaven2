package com._520it.pss.uitl;

import java.lang.reflect.Method;

public class PermissionUtil {
    private PermissionUtil(){}

    public static String buildExperssion(Method method){
        return method.getDeclaringClass().getName()+":"+method.getName();
    }

}
