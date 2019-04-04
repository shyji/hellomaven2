package com._520it.pss.domain;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter@Mark("角色")
public class Role extends BaseDomain{
    private static final long serialVersionUID = 1L;
    private String name;//角色名称			:人事管理
    private String sn;//角色代码(英文名称)	:HR

    private Set<Permission> permissions = new HashSet<>();
    private Set<SystemMenu> systemMenus = new HashSet<>();

}
