package com._520it.pss.web.action;

import com._520it.pss.domain.Employee;
import com._520it.pss.query.EmployeeQueryObject;
import com._520it.pss.service.IDepartmentService;
import com._520it.pss.service.IEmployeeService;
import com._520it.pss.service.IRoleService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAction extends BaseAction{
    @Setter
    private IEmployeeService employeeService;
    @Setter
    private IDepartmentService departmentService;
    @Setter
    private IRoleService roleService;
    @Setter@Getter
    private Employee employee=new Employee();
    @Getter
    private EmployeeQueryObject qo = new EmployeeQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("部门列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
       // System.out.println(super.getActionMessages());
        System.out.println("hello world12www3");
        ActionContext.getContext().put(PAGE_RESULT,employeeService.query(qo));
        ActionContext.getContext().put("depts",departmentService.list());
        return LIST;
    }
    @RequiredPermission("删除部门")
    public String delete() throws Exception{

        try {
            if(employee.getId()!=null){
                employeeService.delete(employee);
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
        ActionContext.getContext().put("depts",departmentService.list());
        ActionContext.getContext().put("roles",roleService.list());
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存部门")@InputConfig(methodName="input")
    public String saveOrUpdate() {
        // 如果没有选择部门,则把员工的部门设置为NULL
        if(employee.getDept().getId()==-1){
            employee.setDept(null);
        }
        try {
            if (employee.getId() != null) {

                employeeService.update(employee);
                super.addActionMessage("更新成功");
            } else {
               // int a=1/0;
                System.out.println(getActionMessages());
                employeeService.save(employee);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    public void validateSaveOrUpdate(){
        if(StringUtils.isBlank(employee.getName())||employee.getName().trim().length()<2){
            addFieldError("employee.name","用户名不能少于2位数!");
        }

    }
    @RequiredPermission("批量删除部门")
    public String batchDelete() throws Exception{
        System.out.println("helloworld");
        System.out.println(ids);
        if(ids.size()>0){
            employeeService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        employee.getRoles().clear();
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());

        }
    }

}
