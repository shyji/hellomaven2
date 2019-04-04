package com._520it.pss.web.action;

import com._520it.pss.domain.Depot;
import com._520it.pss.query.DepotQueryObject;
import com._520it.pss.service.IDepotService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DepotAction extends BaseAction{
    @Setter
    private IDepotService depotService;
    @Setter@Getter
    private Depot depot=new Depot();
    @Getter
    private DepotQueryObject qo = new DepotQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("仓库列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,depotService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除仓库")
    public String delete() throws Exception{

        try {
            if(depot.getId()!=null){
                depotService.delete(depot);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改仓库")
    public String input() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存仓库")
    public String saveOrUpdate() {
        try {
            if (depot.getId() != null) {
                depotService.update(depot);
                super.addActionMessage("更新成功");
            } else {
                depotService.save(depot);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除仓库")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            depotService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
    }

}
