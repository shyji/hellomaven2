package com._520it.pss.dao;

import com._520it.pss.query.OrderChartQueryObject;
import com._520it.pss.vo.OrderChartVO;

import java.util.List;

public interface IChartDAO {
    List<OrderChartVO> query(OrderChartQueryObject qo);
}
