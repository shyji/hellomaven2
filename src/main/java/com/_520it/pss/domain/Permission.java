package com._520it.pss.domain;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//权限/许可
@Getter@Setter@Mark("权限")@ToString
public class Permission extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;//权限名称				:删除员工
	private String expression;//权限表达式		:com._520it.pss.web.action:delete
}
