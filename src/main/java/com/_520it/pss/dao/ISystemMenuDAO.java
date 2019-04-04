package com._520it.pss.dao;

import com._520it.pss.domain.Role;
import com._520it.pss.domain.SystemMenu;

import java.util.List;

public interface ISystemMenuDAO extends IBaseDAO<SystemMenu>{
    List<SystemMenu> queryMenuBySn(String parentSn);

    List<SystemMenu> queryMenuBySn(String parentSn,List<Role> roles);
}