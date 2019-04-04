package com._520it.pss.query;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductStockQueryObject extends QueryObject{
    private long brandId=-1;
    private long depotId=-1;
    protected void customized() {
        if(brandId>0){
            addQuery("obj.product.brand.id=:brandId","brandId",brandId);
        }
        if(depotId>0){
            addQuery("obj.depot.id=:depotId","depotId",depotId);
        }
    }
}
