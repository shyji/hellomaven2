package com._520it.pss.service.impl;

import com._520it.pss.dao.IChartDAO;
import com._520it.pss.query.OrderChartQueryObject;
import com._520it.pss.service.IChartService;
import com._520it.pss.vo.OrderChartVO;
import lombok.Setter;

import java.util.List;

public class ChartServiceImpl implements IChartService{
    @Setter
    private IChartDAO chartDAO;
    @Override
    public List<OrderChartVO> query(OrderChartQueryObject qo) {
        return chartDAO.query(qo);
    }
}
