package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IProductDAO;
import com._520it.pss.domain.Product;
import com._520it.pss.service.IProductService;
import lombok.Setter;

public class ProductServiceImpl extends GenericServiceImpl<Product> implements IProductService{
    @Setter
    private IProductDAO productDAO;


    @Override
    protected IBaseDAO<Product> getDAO() {
        return productDAO;
    }
}
