package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.ISaleAccountDAO;
import com._520it.pss.dao.IStockOutcomeBillDAO;
import com._520it.pss.domain.SaleAccount;
import com._520it.pss.domain.StockOutcomeBill;
import com._520it.pss.domain.StockOutcomeBillItem;
import com._520it.pss.service.IProductStockService;
import com._520it.pss.service.IStockOutcomeBillService;
import com._520it.pss.uitl.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StockOutcomeBillServiceImpl extends GenericServiceImpl<StockOutcomeBill> implements IStockOutcomeBillService{
    @Setter
    private IStockOutcomeBillDAO stockOutcomeBillDAO;

    @Setter
    private IProductStockService productStockService;

    @Setter
    private ISaleAccountDAO saleAccountDAO;


    @Override
    protected IBaseDAO<StockOutcomeBill> getDAO() {
        return stockOutcomeBillDAO;
    }
    @Override
    public void save(StockOutcomeBill e) {
        e.setInputTime(new Date());
        e.setInputUser(UserContext.getCurrentEmployee());
        parse(e);
        stockOutcomeBillDAO.save(e);
    }

    private void parse(StockOutcomeBill e) {

        e.setStatus(StockOutcomeBill.NORMAL);
        List<StockOutcomeBillItem> items = e.getItems();
        BigDecimal totalNumber = new BigDecimal("0.00");
        BigDecimal totalAmount = new BigDecimal("0.00");
        for(StockOutcomeBillItem item :items){
            item.setBill(e);
            item.setTotalAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2));
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(item.getTotalAmount());

        }
        e.setTotalNum(totalNumber);
        e.setTotalAmount(totalAmount);
    }

    @Override
    public void update(StockOutcomeBill e) {
        parse(e);
        stockOutcomeBillDAO.update(e);
    }

    @Override
    public void delete(StockOutcomeBill e) {
        e = stockOutcomeBillDAO.get(e.getId());
        stockOutcomeBillDAO.delete(e);
    }


    @Override
    public void audit(StockOutcomeBill stockOutcomeBill) {
        stockOutcomeBill = stockOutcomeBillDAO.get(stockOutcomeBill.getId());
        if(stockOutcomeBill.getStatus()==StockOutcomeBill.NORMAL){
            stockOutcomeBill.setStatus(stockOutcomeBill.AUDIT);
            stockOutcomeBill.setAuditTime(new Date());
            stockOutcomeBill.setAuditor(UserContext.getCurrentEmployee());
            for(StockOutcomeBillItem item :stockOutcomeBill.getItems()){
                BigDecimal costCount = productStockService.stockOutcome(item.getProduct(), item.getNumber(), item.getSalePrice());
                SaleAccount sa = new SaleAccount();
                System.out.println(costCount);
                sa.setCostAmount(costCount);
                sa.setClient(stockOutcomeBill.getClient());
                sa.setCostPrice(costCount.divide(item.getNumber()));
                sa.setSaler(stockOutcomeBill.getInputUser());
                sa.setSalePrice(item.getSalePrice());
                sa.setVdate(stockOutcomeBill.getVdate());
                sa.setProduct(item.getProduct());
                sa.setNumber(item.getNumber());
                sa.setSaleAmount(item.getTotalAmount());
                saleAccountDAO.save(sa);
            }

            stockOutcomeBillDAO.update(stockOutcomeBill);
        }
    }
}

