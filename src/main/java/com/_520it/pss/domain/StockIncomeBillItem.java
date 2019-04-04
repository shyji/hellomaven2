package com._520it.pss.domain;

import java.math.BigDecimal;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 采购入库单明细
 * @author Administrator
 *
 */
@Setter
@Getter@Mark("采购入库单明细")
public class StockIncomeBillItem extends BaseDomain {

	
	private static final long serialVersionUID = 1L;
	private Product product;
	private BigDecimal salePrice;
	private BigDecimal number;
	private BigDecimal totalAmount;
	private String remark;
	private StockIncomeBill bill;
}
