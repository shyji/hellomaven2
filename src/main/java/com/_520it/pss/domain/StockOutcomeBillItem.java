package com._520it.pss.domain;

import java.math.BigDecimal;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 销售出库单明细
 * @author Administrator
 *
 */
@Setter
@Getter@Mark("销售出库单明细")
public class StockOutcomeBillItem extends BaseDomain {

	
	private static final long serialVersionUID = 1L;
	private Product product;
	private BigDecimal salePrice;
	private BigDecimal number;
	private BigDecimal totalAmount;
	private String remark;
	private StockOutcomeBill bill;
}
