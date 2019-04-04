package com._520it.pss.vo;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
@ToString@Getter
public class OrderChartVO {
    private String groupValue;
    private BigDecimal orderNumber;
    private BigDecimal orderAmount;

    public OrderChartVO(String groupValue, BigDecimal orderNumber, BigDecimal orderAmount) {
        this.groupValue = groupValue;
        this.orderNumber = orderNumber;
        this.orderAmount = orderAmount;
    }
}
