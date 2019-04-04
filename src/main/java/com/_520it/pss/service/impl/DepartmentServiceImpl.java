package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IDepartmentDAO;
import com._520it.pss.domain.Department;
import com._520it.pss.service.IDepartmentService;
import lombok.Setter;

public class DepartmentServiceImpl extends GenericServiceImpl<Department> implements IDepartmentService{
    @Setter
    private IDepartmentDAO departmentDAO;


    @Override
    protected IBaseDAO<Department> getDAO() {
        return departmentDAO;
    }
}
