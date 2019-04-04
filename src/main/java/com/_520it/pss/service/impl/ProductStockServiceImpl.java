package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IProductStockDAO;
import com._520it.pss.domain.Depot;
import com._520it.pss.domain.Product;
import com._520it.pss.domain.ProductStock;
import com._520it.pss.domain.SaleAccount;
import com._520it.pss.service.IProductStockService;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductStockServiceImpl extends GenericServiceImpl<ProductStock> implements IProductStockService{
    @Setter
    private IProductStockDAO productStockDAO;


    @Override
    protected IBaseDAO<ProductStock> getDAO() {
        return productStockDAO;
    }


    @Override
    public void stockIncome(Depot depot, Product product, BigDecimal number, BigDecimal salePrice) {
        ProductStock ps = this.getByProductAndDepot(depot.getId(),product.getId());

        if(ps!=null){
            ps.setIncomeDate(new Date());
            ps.addStoreNum(number);
            ps.addAmount(number.multiply(salePrice));
            ps.countPrice();

            this.update(ps);
        }else{
            ps = new ProductStock();
            ps.setIncomeDate(new Date());
            ps.setDepot(depot);
            ps.setProduct(product);
            ps.setStoreNum(number);
            ps.setPrice(salePrice);
            ps.countAmount();
            this.save(ps);
        }
    }
    /**
     * 出库操作
     */
    public BigDecimal stockOutcome(Product product, BigDecimal number, BigDecimal salePrice){
        List<ProductStock> pss = this.productStockDAO.queryByProduct(product.getId());
        BigDecimal remainStore = BigDecimal.ZERO;;
        for(ProductStock ps :pss){
            remainStore = remainStore.add(ps.getStoreNum());
        }
        if(remainStore.compareTo(number)<0){
            throw new RuntimeException("货品 ["+product.getName()+"] 货源不足!!!");

        }
        BigDecimal totalCostCount = BigDecimal.ZERO;

        for(ProductStock ps :pss){
            if(ps.getStoreNum().compareTo(number)>=0){
               ps.addStoreNum(number.negate());
                ps.countAmount();
                ps.setOutcomeDate(new Date());
                totalCostCount =totalCostCount.add(number.multiply(ps.getPrice()));
                this.update(ps);
                break;

            }else{
                number = number.subtract(ps.getStoreNum());
                ps.setOutcomeDate(new Date());
                ps.setStoreNum(BigDecimal.ZERO);
                ps.setPrice(BigDecimal.ZERO);
                ps.setAmount(BigDecimal.ZERO);
                totalCostCount = totalCostCount.add(ps.getStoreNum().multiply(ps.getPrice()));
                //System.out.println(number+"-"+ps.getStoreNum());
                this.update(ps);
            }
        }
        return totalCostCount;
    }

    public ProductStock getByProductAndDepot(Long depotId, Long productId) {
        return productStockDAO.getByProductAndDepot(depotId,productId);
    }
}
