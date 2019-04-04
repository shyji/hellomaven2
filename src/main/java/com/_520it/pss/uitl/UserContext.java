package com._520it.pss.uitl;

import com._520it.pss.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

public class UserContext {
    private UserContext(){}
    private static final String USER_IN_SESSION = "USER_IN_SESSION";
    private static final String PERMISSIONS_IN_SESSION = "PERMISSIONS_IN_SESSION";
    public static void setEmployee(Employee emp) {
        if (emp == null) {
            ActionContext.getContext().getSession().remove(USER_IN_SESSION);
        }
        ActionContext.getContext().getSession().put(USER_IN_SESSION, emp);
    }
    /**
     * 返回当前Session中的登录用户
     *
     * @return
     */
    public static Employee getCurrentEmployee() {
        return (Employee) ActionContext.getContext().getSession()
                .get(USER_IN_SESSION);
    }

    /**
     * 存储当前用户的所有权限表达式的集合
     */
    public static void setPermissions(Set<String> permissionSet) {
        ActionContext.getContext().getSession()
                .put(PERMISSIONS_IN_SESSION, permissionSet);
    }

    /**
     * 返回当前登录用户的所有的权限表达式集合
     *
     * @return
     */
    public static Set<String> getCurrentPermissions() {
        return (Set<String>) ActionContext.getContext().getSession()
                .get(PERMISSIONS_IN_SESSION);
    }

}
