package com._520it.pss.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject{
    private String keyword;
    private Long deptId=-1L;

    protected void customized() {
        if(StringUtils.isNotBlank(keyword)){
            addQuery("(obj.name like :keyword or obj.email like :keyword)","keyword","%"+keyword+"%");

        }
        if(deptId>0L){
            addQuery("obj.dept.id=:deptId","deptId",deptId);
        }
    }
}
