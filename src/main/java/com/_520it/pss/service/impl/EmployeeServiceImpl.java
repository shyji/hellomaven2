package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IEmployeeDAO;
import com._520it.pss.domain.Employee;
import com._520it.pss.domain.Permission;
import com._520it.pss.domain.Role;
import com._520it.pss.service.IEmployeeService;
import com._520it.pss.uitl.MD5;
import com._520it.pss.uitl.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements IEmployeeService{
    @Setter
    private IEmployeeDAO employeeDAO;


    @Override
    protected IBaseDAO<Employee> getDAO() {
        return employeeDAO;
    }

    @Override
    public Employee checkLogin(String username, String password) {
        Employee current =  employeeDAO.checkLogin(username,MD5.encode(password));
        if(current!=null){
            UserContext.setEmployee(current);
            List<Role> roles = current.getRoles();
            Set<String> expressions = new HashSet<>();
            for(Role role : roles){
                Set<Permission> permissions = role.getPermissions();
                for(Permission permission :permissions){
                    expressions.add(permission.getExpression());
                }

            }
            UserContext.setPermissions(expressions);
        }
        return current;
    }

    @Override
    public void save(Employee e) {
        e.setPassword(MD5.encode(e.getPassword()));
        super.save(e);
    }

    @Override
    public void update(Employee e) {
        //e.setPassword(MD5.encode(e.getPassword()));
        super.update(e);
    }
}
