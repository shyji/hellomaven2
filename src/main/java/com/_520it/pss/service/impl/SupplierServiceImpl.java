package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.ISupplierDAO;
import com._520it.pss.domain.Supplier;
import com._520it.pss.service.ISupplierService;
import lombok.Setter;

public class SupplierServiceImpl extends GenericServiceImpl<Supplier> implements ISupplierService{
    @Setter
    private ISupplierDAO supplierDAO;


    @Override
    protected IBaseDAO<Supplier> getDAO() {
        return supplierDAO;
    }
}
