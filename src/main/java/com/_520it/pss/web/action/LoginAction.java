package com._520it.pss.web.action;

import com._520it.pss.domain.Employee;
import com._520it.pss.service.IEmployeeService;
import lombok.Getter;
import lombok.Setter;

public class LoginAction extends BaseAction{
    @Setter@Getter
    private String username;
    @Setter@Getter
    private String password;
    @Setter
    private IEmployeeService employeeService;

    @Override
    public String execute() throws Exception {
       // System.out.println(123333);
        Employee current = employeeService.checkLogin(username,password);
       // System.out.println(current.getPassword());
        if(current==null){
            addActionError("用户名账号密码错误!");
            return LOGIN;
        }
        return SUCCESS;
    }
}
