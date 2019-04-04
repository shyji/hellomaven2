package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IBrandDAO;
import com._520it.pss.domain.Brand;
import com._520it.pss.service.IBrandService;
import lombok.Setter;

public class BrandServiceImpl extends GenericServiceImpl<Brand> implements IBrandService{
    @Setter
    private IBrandDAO brandDAO;


    @Override
    protected IBaseDAO<Brand> getDAO() {
        return brandDAO;
    }
}
