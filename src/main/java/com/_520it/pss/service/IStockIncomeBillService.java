package com._520it.pss.service;

import com._520it.pss.domain.StockIncomeBill;

public interface IStockIncomeBillService extends IBaseService<StockIncomeBill>{
    void audit(StockIncomeBill stockIncomeBill);
}
