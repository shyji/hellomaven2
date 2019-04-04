package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.ISaleAccountDAO;
import com._520it.pss.domain.SaleAccount;
import com._520it.pss.query.SaleChartQueryObject;
import com._520it.pss.service.ISaleAccountService;
import com._520it.pss.vo.SaleChartVO;
import lombok.Setter;

import java.util.List;

public class SaleAccountServiceImpl extends GenericServiceImpl<SaleAccount> implements ISaleAccountService{
    @Setter
    private ISaleAccountDAO saleAccountDAO;


    @Override
    protected IBaseDAO<SaleAccount> getDAO() {
        return saleAccountDAO;
    }

    @Override
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        return saleAccountDAO.querySaleChart(qo);
    }
}
