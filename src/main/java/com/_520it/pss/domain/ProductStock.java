package com._520it.pss.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 库存明细对象
 * 
 * @author Administrator
 * 
 */
@Setter
@Getter@Mark("库存明细对象")
public class ProductStock extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private BigDecimal storeNum;// 库存数量
	private BigDecimal price;// 库存价格
	private BigDecimal amount;// 总库存明细价格
	private Date incomeDate;
	private Date outcomeDate;
	private Product product;
	private Depot depot;

	public void addStoreNum(BigDecimal amount) {
		this.storeNum = this.storeNum.add(amount);
	}

	public void addAmount(BigDecimal totalAmount) {
		this.amount = this.amount.add(totalAmount);
	}

	public void countPrice() {
		if (storeNum.equals(BigDecimal.ZERO)) {
			price = BigDecimal.ZERO;
		} else {
			price = amount.divide(storeNum, 2, RoundingMode.HALF_UP);
		}
	}

	public void countAmount() {
		this.amount = this.storeNum.multiply(price).setScale(2,
				RoundingMode.HALF_UP);
	}
}
