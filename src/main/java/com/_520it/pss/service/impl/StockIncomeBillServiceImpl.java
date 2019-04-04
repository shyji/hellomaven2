package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IStockIncomeBillDAO;
import com._520it.pss.domain.StockIncomeBill;
import com._520it.pss.domain.StockIncomeBillItem;
import com._520it.pss.domain.StockIncomeBill;
import com._520it.pss.service.IProductService;
import com._520it.pss.service.IProductStockService;
import com._520it.pss.service.IStockIncomeBillService;
import com._520it.pss.uitl.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StockIncomeBillServiceImpl extends GenericServiceImpl<StockIncomeBill> implements IStockIncomeBillService{
    @Setter
    private IStockIncomeBillDAO stockIncomeBillDAO;

    @Setter
    private IProductStockService productStockService;
    @Override
    protected IBaseDAO<StockIncomeBill> getDAO() {
        return stockIncomeBillDAO;
    }

    @Override
    public void save(StockIncomeBill e) {
        e.setInputTime(new Date());
        e.setInputUser(UserContext.getCurrentEmployee());
        parse(e);
        stockIncomeBillDAO.save(e);
    }

    private void parse(StockIncomeBill e) {

        e.setStatus(StockIncomeBill.NORMAL);
        List<StockIncomeBillItem> items = e.getItems();
        BigDecimal totalNumber = new BigDecimal("0.00");
        BigDecimal totalAmount = new BigDecimal("0.00");
        for(StockIncomeBillItem item :items){
            item.setBill(e);
            item.setTotalAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2));
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(item.getTotalAmount());

        }
        e.setTotalNum(totalNumber);
        e.setTotalAmount(totalAmount);
    }

    @Override
    public void update(StockIncomeBill e) {
        parse(e);
        stockIncomeBillDAO.update(e);
    }

    @Override
    public void delete(StockIncomeBill e) {
        e = stockIncomeBillDAO.get(e.getId());
        stockIncomeBillDAO.delete(e);
    }


    @Override
    public void audit(StockIncomeBill stockIncomeBill) {
        stockIncomeBill = stockIncomeBillDAO.get(stockIncomeBill.getId());
        if(stockIncomeBill.getStatus()==StockIncomeBill.NORMAL){
            stockIncomeBill.setStatus(stockIncomeBill.AUDIT);
            stockIncomeBill.setAuditTime(new Date());
            stockIncomeBill.setAuditor(UserContext.getCurrentEmployee());
            for(StockIncomeBillItem item :stockIncomeBill.getItems()){
                productStockService.stockIncome(stockIncomeBill.getDepot(),item.getProduct(),item.getNumber(),item.getSalePrice());
            }
            stockIncomeBillDAO.update(stockIncomeBill);
        }
    }
}
