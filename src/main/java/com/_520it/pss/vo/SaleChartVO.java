package com._520it.pss.vo;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
@Getter
public class SaleChartVO extends OrderChartVO{
    private BigDecimal profit;

    public SaleChartVO(String groupValue, BigDecimal saleNumber, BigDecimal saleAmount,BigDecimal profit) {
        super(groupValue, saleNumber, saleAmount);
        this.profit = profit;

    }
}
