package com._520it.pss.web.action;

import com._520it.pss.dao.ISupplierDAO;
import com._520it.pss.domain.OrderBill;
import com._520it.pss.domain.OrderBillItem;
import com._520it.pss.query.OrderBillQueryObject;
import com._520it.pss.service.IOrderBillService;
import com._520it.pss.service.ISupplierService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class OrderBillAction extends BaseAction{
    @Setter
    private IOrderBillService orderBillService;
    @Setter
    private ISupplierService supplierService;
    @Setter@Getter
    private OrderBill orderBill=new OrderBill();
    @Getter
    private OrderBillQueryObject qo = new OrderBillQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("采购订单列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,orderBillService.query(qo));
        ActionContext.getContext().put("suppliers",supplierService.list());
        return LIST;
    }
    @RequiredPermission("删除采购订单")
    public String delete() throws Exception{

        try {
            if(orderBill.getId()!=null){
                orderBillService.delete(orderBill);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改采购订单")
    public String input() throws Exception {
        ActionContext.getContext().put("suppliers",supplierService.list());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
        }
        return INPUT;
    }
    @RequiredPermission("查看采购订单")
    public String view() throws Exception {
        //ActionContext.getContext().put("suppliers",supplierService.list());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
        }
        return "view";
    }
    @RequiredPermission("查看采购审核")
    public String audit() throws Exception{

        orderBillService.audit(orderBill);
        return SUCCESS;
    }

    @RequiredPermission("保存采购订单")
    public String saveOrUpdate() {
        try {
            if (orderBill.getId() != null) {
                orderBillService.update(orderBill);
                super.addActionMessage("更新成功");
            } else {

                orderBillService.save(orderBill);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
            e.printStackTrace();
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除采购订单")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            orderBillService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
            orderBill.setSupplier(null);
        }
        orderBill.getItems().clear();
    }

}
