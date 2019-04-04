package com._520it.pss.dao.impl;

import com._520it.pss.dao.IProductStockDAO;
import com._520it.pss.domain.ProductStock;
import org.hibernate.Session;

import java.util.List;

public class ProductStockDAOImpl extends GenericDAOImpl<ProductStock> implements IProductStockDAO{

    @Override
    public ProductStock getByProductAndDepot(Long depotId, Long productId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (ProductStock) currentSession.createQuery("SELECT ps FROM ProductStock ps WHERE ps.depot.id = :depotId AND ps.product.id = :productId")
                .setParameter("productId",productId).setParameter("depotId",depotId).uniqueResult();
    }

    @Override
    public List<ProductStock> queryByProduct(Long productId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("SELECT ps FROM ProductStock ps WHERE ps.product.id=  :productId ORDER BY ps.storeNum DESC")
                .setParameter("productId",productId).list();
    }
}
