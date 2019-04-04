package com._520it.pss.domain;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Mark("部门")
public class Department extends BaseDomain{
    private static final long serialVersionUID = 1L;
    private String name;
    private String sn;
}
