package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IOrderBillDAO;
import com._520it.pss.domain.OrderBill;
import com._520it.pss.domain.OrderBillItem;
import com._520it.pss.service.IOrderBillService;
import com._520it.pss.uitl.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderBillServiceImpl extends GenericServiceImpl<OrderBill> implements IOrderBillService{
    @Setter
    private IOrderBillDAO orderBillDAO;


    @Override
    protected IBaseDAO<OrderBill> getDAO() {
        return orderBillDAO;
    }

    @Override
    public void save(OrderBill e) {
        e.setInputTime(new Date());
        e.setInputUser(UserContext.getCurrentEmployee());
        parse(e);
        orderBillDAO.save(e);
    }

    private void parse(OrderBill e) {

        e.setStatus(OrderBill.NORMAL);
        List<OrderBillItem> items = e.getItems();
        BigDecimal totalNumber = new BigDecimal("0.00");
        BigDecimal totalAmount = new BigDecimal("0.00");
        for(OrderBillItem item :items){
            item.setBill(e);
            item.setTotalAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2));
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(item.getTotalAmount());

        }
        e.setTotalNum(totalNumber);
        e.setTotalAmount(totalAmount);
    }

    @Override
    public void update(OrderBill e) {
        parse(e);
        orderBillDAO.update(e);
    }

    @Override
    public void delete(OrderBill e) {
        e = orderBillDAO.get(e.getId());
        orderBillDAO.delete(e);
    }


    @Override
    public void audit(OrderBill orderBill) {
        orderBill = orderBillDAO.get(orderBill.getId());
        if(orderBill.getStatus()==OrderBill.NORMAL){
            orderBill.setStatus(orderBill.AUDIT);
            orderBill.setAuditTime(new Date());
            orderBill.setAuditor(UserContext.getCurrentEmployee());
            orderBillDAO.update(orderBill);
        }
    }
}
