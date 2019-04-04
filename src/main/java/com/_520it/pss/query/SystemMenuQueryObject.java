package com._520it.pss.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class SystemMenuQueryObject extends QueryObject{
    private long parentId=-1;

    private String parentSn;

    protected void customized() {
        if(parentId>0L){
            addQuery("obj.parent.id=:parentId","parentId",parentId);
        }else{
            //查看根目录
            addQuery("obj.parent is null");
        }
        if(StringUtils.isNotBlank(parentSn)){
            addQuery("obj.parent.sn=:parentSn","parentSn",parentSn);
        }
    }
}
