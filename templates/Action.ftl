package ${basePackage}.web.action;

import ${basePackage}.domain.${className};
import ${basePackage}.query.${className}QueryObject;
import ${basePackage}.service.I${className}Service;
import ${basePackage}.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ${className}Action extends BaseAction{
    @Setter
    private I${className}Service ${objectName}Service;
    @Setter@Getter
    private ${className} ${objectName}=new ${className}();
    @Getter
    private ${className}QueryObject qo = new ${className}QueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("${chineseName}列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,${objectName}Service.query(qo));
        return LIST;
    }
    @RequiredPermission("删除${chineseName}")
    public String delete() throws Exception{

        try {
            if(${objectName}.getId()!=null){
                ${objectName}Service.delete(${objectName});
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改${chineseName}")
    public String input() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存${chineseName}")
    public String saveOrUpdate() {
        try {
            if (${objectName}.getId() != null) {
                ${objectName}Service.update(${objectName});
                super.addActionMessage("更新成功");
            } else {
                ${objectName}Service.save(${objectName});
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除${chineseName}")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            ${objectName}Service.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
    }

}
