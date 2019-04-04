package com._520it.pss.service;

import com._520it.pss.domain.SaleAccount;
import com._520it.pss.query.SaleChartQueryObject;
import com._520it.pss.vo.SaleChartVO;

import java.util.List;

public interface ISaleAccountService extends IBaseService<SaleAccount>{
    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
}
