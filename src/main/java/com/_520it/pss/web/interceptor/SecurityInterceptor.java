package com._520it.pss.web.interceptor;

import com._520it.pss.domain.Employee;
import com._520it.pss.uitl.PermissionUtil;
import com._520it.pss.uitl.RequiredPermission;
import com._520it.pss.uitl.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

public class SecurityInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Employee current = UserContext.getCurrentEmployee();
        if(current!=null && current.getAdmin()){
            return invocation.invoke();
        }
        Method method = invocation.getAction().getClass().getMethod(invocation.getProxy().getMethod());
        if(method==null || !method.isAnnotationPresent(RequiredPermission.class)){
            return invocation.invoke();
        }
        Set<String> currentPermissions = UserContext.getCurrentPermissions();
        if(currentPermissions.contains(PermissionUtil.buildExperssion(method))){
            return invocation.invoke();
        }
        return "nopermission";
    }
}
