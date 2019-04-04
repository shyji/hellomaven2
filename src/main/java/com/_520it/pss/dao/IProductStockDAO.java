package com._520it.pss.dao;

import com._520it.pss.domain.Product;
import com._520it.pss.domain.ProductStock;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockDAO extends IBaseDAO<ProductStock>{
    ProductStock getByProductAndDepot(Long depotId, Long productId);


    List<ProductStock> queryByProduct(Long id);
}