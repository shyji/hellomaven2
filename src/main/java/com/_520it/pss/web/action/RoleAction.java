package com._520it.pss.web.action;

import com._520it.pss.domain.Role;
import com._520it.pss.query.RoleQueryObject;
import com._520it.pss.service.IPermissionService;
import com._520it.pss.service.IRoleService;
import com._520it.pss.service.ISystemMenuService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class RoleAction extends BaseAction{
    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Setter
    private ISystemMenuService systemMenuService;
    @Setter@Getter
    private Role role=new Role();
    @Getter
    private RoleQueryObject qo = new RoleQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("角色列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        System.out.println("lllllsssssssss");
       // System.out.println(super.getActionMessages());
        ActionContext.getContext().put(PAGE_RESULT,roleService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除角色")
    public String delete() throws Exception{

        try {
            if(role.getId()!=null){
                roleService.delete(role);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改角色")
    public String input() throws Exception {
        ActionContext.getContext().put("permissions",permissionService.list());
        ActionContext.getContext().put("systemMenus",systemMenuService.list());
        if (role.getId() != null) {
            role = roleService.get(role.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存角色")
    public String saveOrUpdate() {
        System.out.println(role.getPermissions());
        try {
            if (role.getId() != null) {
                roleService.update(role);
                super.addActionMessage("更新成功");
            } else {
                roleService.save(role);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除角色")
    public String batchDelete() throws Exception{
        System.out.println("helloworld");
        System.out.println(ids);
        if(ids.size()>0){
            roleService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (role.getId() != null) {
            role = roleService.get(role.getId());
        }
        role.getPermissions().clear();
        role.getSystemMenus().clear();
    }

}
