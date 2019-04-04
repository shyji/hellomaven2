package com._520it.pss.domain;

import java.math.BigDecimal;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单明细
 * @author Administrator
 *
 */
@Getter
@Setter@Mark("采购订单明细")
public class OrderBillItem extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private BigDecimal salePrice;//订货价格
	private BigDecimal number;//订货明细数量
	private BigDecimal totalAmount;//
	private String remark;
	private OrderBill bill;
}
