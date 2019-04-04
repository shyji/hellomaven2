package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IRoleDAO;
import com._520it.pss.domain.Role;
import com._520it.pss.service.IRoleService;
import lombok.Setter;

public class RoleServiceImpl extends GenericServiceImpl<Role> implements IRoleService{
    @Setter
    private IRoleDAO roleDAO;


    @Override
    protected IBaseDAO<Role> getDAO() {
        return roleDAO;
    }
}
