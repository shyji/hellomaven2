package com._520it.pss.query;
import com._520it.pss.uitl.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends QueryObject{

    private Date beginDate;
    private Date endDate;
    private long clientId=-1;

    private int status=-1;

    protected void customized() {
        if(beginDate!=null){
            addQuery("obj.vdate >= :beginDate","beginDate",beginDate);
        }
        if(endDate!=null){
            addQuery("obj.vdate <= :endDate","endDate", DateUtil.endOfDay(endDate));
        }
        if(clientId>0){
            addQuery("obj.client.id=:clientId","clientId",clientId);

        }
        if(status>0){
            addQuery("obj.status=:status","status",status);
        }

    }}
