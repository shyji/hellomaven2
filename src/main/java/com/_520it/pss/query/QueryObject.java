package com._520it.pss.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryObject {
    @Setter@Getter
    private Integer currentPage=1;
    @Setter@Getter
    private Integer pageSize=5;
    private boolean isinit=false;
    private void init(){
        if(!isinit){
            this.customized();
            isinit=true;
        }
    }
    private List<String> conditions=new ArrayList<>();
    private Map<String,Object> parameterMap = new HashMap<>();

    public Map<String, Object> getParameterMap() {
        init();
        return parameterMap;
    }
    protected void addQuery(String condition,String key,Object value){
        conditions.add(condition);
        parameterMap.put(key,value);
    }

    protected void addQuery(String condition){
        conditions.add(condition);

    }
    public String getQuery(){
        init();

        if(conditions.size()==0){
            return "";
        }
        return " where "+ StringUtils.join(conditions," and ");

    }
    protected void customized(){}

}
