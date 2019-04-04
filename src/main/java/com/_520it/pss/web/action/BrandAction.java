package com._520it.pss.web.action;

import com._520it.pss.domain.Brand;
import com._520it.pss.query.BrandQueryObject;
import com._520it.pss.service.IBrandService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class BrandAction extends BaseAction{
    @Setter
    private IBrandService brandService;
    @Setter@Getter
    private Brand brand=new Brand();
    @Getter
    private BrandQueryObject qo = new BrandQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("品牌列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,brandService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除品牌")
    public String delete() throws Exception{

        try {
            if(brand.getId()!=null){
                brandService.delete(brand);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改品牌")
    public String input() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存品牌")
    public String saveOrUpdate() {
        try {
            if (brand.getId() != null) {
                brandService.update(brand);
                super.addActionMessage("更新成功");
            } else {
                brandService.save(brand);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除品牌")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            brandService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
    }

}
