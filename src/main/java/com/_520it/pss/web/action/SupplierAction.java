package com._520it.pss.web.action;

import com._520it.pss.domain.Supplier;
import com._520it.pss.query.SupplierQueryObject;
import com._520it.pss.service.ISupplierService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SupplierAction extends BaseAction{
    @Setter
    private ISupplierService supplierService;
    @Setter@Getter
    private Supplier supplier=new Supplier();
    @Getter
    private SupplierQueryObject qo = new SupplierQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("供应商列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,supplierService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除供应商")
    public String delete() throws Exception{

        try {
            if(supplier.getId()!=null){
                supplierService.delete(supplier);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改供应商")
    public String input() throws Exception {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存供应商")
    public String saveOrUpdate() {
        try {
            if (supplier.getId() != null) {
                supplierService.update(supplier);
                super.addActionMessage("更新成功");
            } else {
                supplierService.save(supplier);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除供应商")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            supplierService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
    }

}
