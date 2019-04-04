package com._520it.pss.query;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class ProductQueryObject extends QueryObject{
    private String keyword;
    private long brandId=-1;

    protected void customized() {
        if(StringUtils.isNotBlank(keyword)){
            addQuery("(obj.name like :keyword or intro like :keyword)","keyword","%"+keyword+"%");
        }
        if(brandId>0){
            addQuery("obj.brand.id=:brandId","brandId",brandId);
        }
    }
}
