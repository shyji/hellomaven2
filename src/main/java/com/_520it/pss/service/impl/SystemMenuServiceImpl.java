package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.ISystemMenuDAO;
import com._520it.pss.domain.SystemMenu;
import com._520it.pss.service.ISystemMenuService;
import lombok.Setter;

import java.util.List;

public class SystemMenuServiceImpl extends GenericServiceImpl<SystemMenu> implements ISystemMenuService{
    @Setter
    private ISystemMenuDAO systemMenuDAO;


    @Override
    protected IBaseDAO<SystemMenu> getDAO() {
        return systemMenuDAO;
    }

    @Override
    public List<SystemMenu> queryMenuBySn(String parentSn) {
        return systemMenuDAO.queryMenuBySn(parentSn);
    }
}
