package com._520it.pss.dao.impl;

import com._520it.pss.dao.IEmployeeDAO;
import com._520it.pss.domain.Employee;
import org.hibernate.Session;

public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements IEmployeeDAO{

    @Override
    public Employee checkLogin(String username, String password) {
        Session currentSession = sessionFactory.getCurrentSession();
        Employee current = (Employee) currentSession.createQuery("select obj from Employee obj where name=:name and password=:password")
                .setParameter("name",username).setParameter("password",password).uniqueResult();

        return current;
    }
}
