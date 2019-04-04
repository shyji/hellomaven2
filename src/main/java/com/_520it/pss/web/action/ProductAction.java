package com._520it.pss.web.action;

import com._520it.pss.domain.Product;
import com._520it.pss.query.ProductQueryObject;
import com._520it.pss.service.IBrandService;
import com._520it.pss.service.IProductService;
import com._520it.pss.uitl.FileUploadUtil;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductAction extends BaseAction{
    @Setter
    private IProductService productService;
    @Setter
    private IBrandService brandService;
    @Setter@Getter
    private Product product=new Product();
    @Setter
    private File productImg;
    @Setter
    private String productImgFileName;
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("商品列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,productService.query(qo));
        ActionContext.getContext().put("brands",brandService.list());
        return LIST;
    }@RequiredPermission("选择商品商品")
    public String selectProductList() throws Exception {
        execute();
        return "selectProductList";
    }
    @RequiredPermission("删除商品")
    public String delete() throws Exception{

        try {
            if(product.getId()!=null){
                productService.delete(product);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改商品")
    public String input() throws Exception {
        ActionContext.getContext().put("brands",brandService.list());
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存商品")
    public String saveOrUpdate() {
        try {
            System.out.println(productImgFileName);
            //更新要删除图片
            if(product.getPic()!=null&&productImg!=null){
                FileUploadUtil.deleteFile(product.getPic());
            }
            if(productImg!=null){
                String fileName= FileUploadUtil.uploadFile(productImg, productImgFileName);
                System.out.println(fileName);
                product.setPic(fileName);
            }
            if (product.getId() != null) {
                productService.update(product);
                super.addActionMessage("更新成功");
            } else {
                productService.save(product);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除商品")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            productService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (product.getId() != null) {
            product = productService.get(product.getId());
            product.setBrand(null);
        }
    }

}
