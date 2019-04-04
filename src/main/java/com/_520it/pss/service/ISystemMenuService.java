package com._520it.pss.service;

import com._520it.pss.domain.SystemMenu;

import java.util.List;

public interface ISystemMenuService extends IBaseService<SystemMenu>{
    List<SystemMenu> queryMenuBySn(String parentSn);
}
