package com._520it.pss.dao;

import com._520it.pss.domain.Employee;

public interface IEmployeeDAO extends IBaseDAO<Employee>{
    Employee checkLogin(String username, String password);
}
