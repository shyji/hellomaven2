package com._520it.pss.web.action;

import com._520it.pss.domain.SystemMenu;
import com._520it.pss.query.SystemMenuQueryObject;
import com._520it.pss.service.ISystemMenuService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemMenuAction extends BaseAction{
    @Setter
    private ISystemMenuService systemMenuService;
    @Setter@Getter
    private SystemMenu systemMenu=new SystemMenu();
    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("系统菜单列表")@InputConfig(methodName="input")
    public String execute() throws Exception {

        if(qo.getParentId()>-1){
            systemMenu = systemMenuService.get(qo.getParentId());
        }
        System.out.println(systemMenu.getAllParents());
        ActionContext.getContext().put(PAGE_RESULT,systemMenuService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除系统菜单")
    public String delete() throws Exception{

        try {

            if(systemMenu.getId()!=null){
                systemMenuService.delete(systemMenu);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改系统菜单")
    public String input() throws Exception {
        if(qo.getParentId()>-1){
            systemMenu.setParent(systemMenuService.get(qo.getParentId()));
        }
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存系统菜单")
    public String saveOrUpdate() {
        try {
            if(systemMenu.getParent().getId()==null){
                systemMenu.setParent(null);
            }
            if (systemMenu.getId() != null) {
                systemMenuService.update(systemMenu);
                super.addActionMessage("更新成功");
            } else {
                systemMenuService.save(systemMenu);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除系统菜单")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            systemMenuService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
    }

    public String loadCurrentEmployeeMenus(){

        if(StringUtils.isNotBlank(qo.getParentSn())){
           // System.out.println(qo.getParentSn()+"123fdff");

            List<SystemMenu> systemMenus = null;
            try {
                systemMenus = systemMenuService.queryMenuBySn(qo.getParentSn());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(systemMenus);
            List<Map> json=new ArrayList<>();
            for(SystemMenu menu: systemMenus ){
                json.add(menu.toJsonMap());
            }
            //System.out.println(json);
            ActionContext.getContext().put("rootObj",json);
        }

        return JSON;
    }

}
