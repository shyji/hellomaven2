package com._520it.pss.web.interceptor;

import com._520it.pss.uitl.UserContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
登录检查拦截器
 */
public class CheckLoginInterceptor extends AbstractInterceptor{
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if(UserContext.getCurrentEmployee()==null){
            ActionSupport action = (ActionSupport)invocation.getAction();
            action.addActionError("用户必须登录!!!");
            return "login";
        }

        return invocation.invoke();
    }
}
