package com._520it.pss.service;

import com._520it.pss.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee>{
    Employee checkLogin(String username, String password);
}
