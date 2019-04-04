package com._520it.pss.web.action;

import com._520it.pss.domain.ProductStock;
import com._520it.pss.query.ProductStockQueryObject;
import com._520it.pss.service.IBrandService;
import com._520it.pss.service.IDepotService;
import com._520it.pss.service.IProductStockService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ProductStockAction extends BaseAction{
    @Setter
    private IProductStockService productStockService;
    @Setter
    private IBrandService brandService;
    @Setter
    private IDepotService depotService;
    @Setter@Getter
    private ProductStock productStock=new ProductStock();
    @Getter
    private ProductStockQueryObject qo = new ProductStockQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("库存明细对象列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,productStockService.query(qo));
        ActionContext.getContext().put("brands",brandService.list());
        ActionContext.getContext().put("depots",depotService.list());
        return LIST;
    }

}
