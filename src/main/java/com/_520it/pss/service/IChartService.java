package com._520it.pss.service;

import com._520it.pss.query.OrderChartQueryObject;
import com._520it.pss.vo.OrderChartVO;

import java.util.List;

public interface IChartService {
    List<OrderChartVO> query(OrderChartQueryObject qo);
}
