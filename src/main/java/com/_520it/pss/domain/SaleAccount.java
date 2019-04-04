package com._520it.pss.domain;

import java.math.BigDecimal;
import java.util.Date;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 销售账
 * 
 * @author Administrator
 * 
 */
@Setter
@Getter@Mark("销售账")
public class SaleAccount extends BaseDomain {

	private Product product;
	private Date vdate;
	private BigDecimal number;
	private BigDecimal salePrice;// 销售价格
	private BigDecimal saleAmount;// 销售总金额
	private BigDecimal costPrice;// 销售成本
	private BigDecimal costAmount;// 总成本金额
	private Client client;// 客户
	private Employee saler;// 销售人员
}
