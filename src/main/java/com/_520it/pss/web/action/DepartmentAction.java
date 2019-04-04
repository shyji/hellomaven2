package com._520it.pss.web.action;

import com._520it.pss.domain.Department;
import com._520it.pss.query.DepartmentQueryObject;
import com._520it.pss.service.IDepartmentService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DepartmentAction extends BaseAction{
    @Setter
    private IDepartmentService departmentService;
    @Setter@Getter
    private Department department=new Department();
    @Getter
    private DepartmentQueryObject qo = new DepartmentQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("部门列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
       // System.out.println(super.getActionMessages());
        ActionContext.getContext().put(PAGE_RESULT,departmentService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除部门")
    public String delete() throws Exception{

        try {
            if(department.getId()!=null){
                departmentService.delete(department);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改部门")
    public String input() throws Exception {
        if (department.getId() != null) {
            department = departmentService.get(department.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存部门")
    public String saveOrUpdate() {
        try {
            if (department.getId() != null) {
                departmentService.update(department);
                super.addActionMessage("更新成功");
            } else {
                departmentService.save(department);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除部门")
    public String batchDelete() throws Exception{
        System.out.println("helloworld");
        System.out.println(ids);
        if(ids.size()>0){
            departmentService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (department.getId() != null) {
            department = departmentService.get(department.getId());
        }
    }

}
