package com._520it.pss.web.action;

import com._520it.pss.domain.StockIncomeBill;
import com._520it.pss.query.StockIncomeBillQueryObject;
import com._520it.pss.service.IDepotService;
import com._520it.pss.service.IStockIncomeBillService;
import com._520it.pss.service.ISupplierService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class StockIncomeBillAction extends BaseAction{
    @Setter
    private IStockIncomeBillService stockIncomeBillService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IDepotService depotService;
    @Setter@Getter
    private StockIncomeBill stockIncomeBill=new StockIncomeBill();
    @Getter
    private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("采购入库单列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,stockIncomeBillService.query(qo));
        ActionContext.getContext().put("suppliers",supplierService.list());
        ActionContext.getContext().put("depots",depotService.list());

        return LIST;
    }
    @RequiredPermission("删除采购入库单")
    public String delete() throws Exception{

        try {
            if(stockIncomeBill.getId()!=null){
                stockIncomeBillService.delete(stockIncomeBill);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改采购入库单")
    public String input() throws Exception {
        ActionContext.getContext().put("suppliers",supplierService.list());
        ActionContext.getContext().put("depots",depotService.list());

        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("查看采购入库订单")
    public String view() throws Exception {
        //ActionContext.getContext().put("suppliers",supplierService.list());
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return "view";
    }
    @RequiredPermission("审核采购入库审核")
    public String audit() throws Exception{

        try {
            stockIncomeBillService.audit(stockIncomeBill);
        } catch (Exception e) {
            super.addActionError("审核失败!原因:"+e.getMessage());
            e.printStackTrace();
        }
        return SUCCESS;
    }


    @RequiredPermission("保存采购入库单")
    public String saveOrUpdate() {
        try {
            if (stockIncomeBill.getId() != null) {
                stockIncomeBillService.update(stockIncomeBill);
                super.addActionMessage("更新成功");
            } else {
                stockIncomeBillService.save(stockIncomeBill);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除采购入库单")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            stockIncomeBillService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
            stockIncomeBill.setSupplier(null);
            stockIncomeBill.setDepot(null);
        }
        stockIncomeBill.getItems().clear();
    }

}
