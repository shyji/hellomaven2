package com._520it.pss.web.action;

import com._520it.pss.domain.Permission;
import com._520it.pss.query.PermissionQueryObject;
import com._520it.pss.service.IPermissionService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class PermissionAction extends BaseAction{
    @Setter
    private IPermissionService permissionService;
    @Setter@Getter
    private Permission permission=new Permission();
    @Getter
    private PermissionQueryObject qo = new PermissionQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("权限列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,permissionService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除权限")
    public String delete() throws Exception{

        try {
            if(permission.getId()!=null){
                permissionService.delete(permission);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }@RequiredPermission("重新加载权限")
    public String reload() throws Exception{
        System.out.println("加载权限中!!!");
        permissionService.reload();


        return NONE;
    }




    @RequiredPermission("批量删除权限")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            permissionService.batchDelete(ids);
        }
        return NONE;
    }



}
