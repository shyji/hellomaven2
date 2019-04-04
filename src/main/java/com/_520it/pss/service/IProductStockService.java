package com._520it.pss.service;

import com._520it.pss.domain.Depot;
import com._520it.pss.domain.Product;
import com._520it.pss.domain.ProductStock;
import com._520it.pss.domain.SaleAccount;

import java.math.BigDecimal;

public interface IProductStockService extends IBaseService<ProductStock>{
     void stockIncome(Depot depot, Product product, BigDecimal number,
                            BigDecimal salePrice);
     BigDecimal stockOutcome(Product product, BigDecimal number, BigDecimal salePrice);
     ProductStock getByProductAndDepot(Long depotId, Long productId);
}
