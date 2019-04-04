package com._520it.pss.dao;

import com._520it.pss.domain.SaleAccount;
import com._520it.pss.query.SaleChartQueryObject;
import com._520it.pss.vo.SaleChartVO;

import java.util.List;

public interface ISaleAccountDAO extends IBaseDAO<SaleAccount>{
        List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
    }