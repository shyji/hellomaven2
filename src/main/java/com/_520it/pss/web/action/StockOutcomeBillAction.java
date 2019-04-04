package com._520it.pss.web.action;

import com._520it.pss.domain.StockOutcomeBill;
import com._520it.pss.query.StockOutcomeBillQueryObject;
import com._520it.pss.service.IClientService;
import com._520it.pss.service.IStockOutcomeBillService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class StockOutcomeBillAction extends BaseAction{
    @Setter
    private IStockOutcomeBillService stockOutcomeBillService;
    @Setter
    private IClientService clientService;
    @Setter@Getter
    private StockOutcomeBill stockOutcomeBill=new StockOutcomeBill();
    @Getter
    private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("销售出库单列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        
        ActionContext.getContext().put(PAGE_RESULT,stockOutcomeBillService.query(qo));
        ActionContext.getContext().put("clients",clientService.list());
        return LIST;
    }
    @RequiredPermission("删除销售出库单")
    public String delete() throws Exception{

        try {
            if(stockOutcomeBill.getId()!=null){
                stockOutcomeBillService.delete(stockOutcomeBill);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改销售出库单")
    public String input() throws Exception {
        ActionContext.getContext().put("clients",clientService.list());
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return INPUT;
    }
    @RequiredPermission("查看销售出库单")
    public String view() throws Exception {
        //ActionContext.getContext().put("suppliers",supplierService.list());
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return "view";
    }
    @RequiredPermission("审核销售出库单")
    public String audit() throws Exception{

        stockOutcomeBillService.audit(stockOutcomeBill);
        return SUCCESS;
    }


    @RequiredPermission("保存销售出库单")
    public String saveOrUpdate() {
        try {
            if (stockOutcomeBill.getId() != null) {
                stockOutcomeBillService.update(stockOutcomeBill);
                super.addActionMessage("更新成功");
            } else {
                stockOutcomeBillService.save(stockOutcomeBill);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除销售出库单")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            stockOutcomeBillService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
            stockOutcomeBill.setClient(null);
        }
        stockOutcomeBill.getItems().clear();
    }

}
