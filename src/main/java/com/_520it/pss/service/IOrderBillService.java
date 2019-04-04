package com._520it.pss.service;

import com._520it.pss.domain.OrderBill;

public interface IOrderBillService extends IBaseService<OrderBill>{
    void audit(OrderBill orderBill);
}
