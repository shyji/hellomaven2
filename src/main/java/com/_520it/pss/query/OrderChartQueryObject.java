package com._520it.pss.query;

import com._520it.pss.domain.OrderBill;
import com._520it.pss.uitl.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
@Getter@Setter
public class OrderChartQueryObject extends QueryObject{
    private Date beginDate;
    private Date endDate;
    private String productName;
    private long supplierId=-1;
    private long brandId = -1L;
    private String group="employee";

    protected void customized() {

        if(beginDate!=null){
            addQuery("obj.bill.vdate >= :beginDate","beginDate",beginDate);
        }
        if(endDate!=null){
            addQuery("obj.bill.vdate <= :endDate","endDate", DateUtil.endOfDay(endDate));
        }
        if(supplierId>0){
            addQuery("obj.bill.supplier.id=:supplierId","supplierId",supplierId);


        }
        if(StringUtils.isNotBlank(productName)){
            addQuery("obj.product.name like :productName or obj.product.sn like :productName","productName","%"+productName+"%");
        }
        if(brandId>0){
            addQuery("obj.product.brand.id=:brandId","brandId",brandId);
        }

            addQuery("obj.bill.status=:status","status", OrderBill.AUDIT);


    }
}
