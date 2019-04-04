package com._520it.pss.service;

import com._520it.pss.domain.StockOutcomeBill;

public interface IStockOutcomeBillService extends IBaseService<StockOutcomeBill>{
    public void audit(StockOutcomeBill stockOutcomeBill);
}
