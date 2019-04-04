package com._520it.pss.dao.impl;

import com._520it.pss.dao.ISystemMenuDAO;
import com._520it.pss.domain.Role;
import com._520it.pss.domain.SystemMenu;
import org.hibernate.Session;

import java.util.List;

public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements ISystemMenuDAO{

    @Override
    public List<SystemMenu> queryMenuBySn(String parentSn) {
        Session currentSession = sessionFactory.getCurrentSession();
        List list =currentSession.createQuery("select obj from SystemMenu obj where obj.parent.sn=:parentSn")
                .setParameter("parentSn",parentSn).list();
        System.out.println(parentSn);
        return list;
    }

    @Override
    public List<SystemMenu> queryMenuBySn(String parentSn, List<Role> roles) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("select s from Role r  join r.systemMenus s where s.parent.sn=:parentSn and r in:(roles)")
                .setParameter("parentSn",parentSn).setParameterList("roles",roles).list();
    }
    }

