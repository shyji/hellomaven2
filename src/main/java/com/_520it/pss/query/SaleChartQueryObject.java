package com._520it.pss.query;

import com._520it.pss.domain.OrderBill;
import com._520it.pss.uitl.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
@Getter@Setter
public class SaleChartQueryObject  extends QueryObject{
    private Date beginDate;
    private Date endDate;
    private String productName;
    private long clientId=-1L;
    private long brandId = -1L;
    private String group="employee";

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
        if(StringUtils.isNotBlank(productName)){
            addQuery("obj.product.name like :productName or obj.product.sn like :productName","productName","%"+productName+"%");
        }
        if(brandId>0){
            addQuery("obj.product.brand.id=:brandId","brandId",brandId);
        }




    }


}
