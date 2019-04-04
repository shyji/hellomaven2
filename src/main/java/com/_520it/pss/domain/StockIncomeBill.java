package com._520it.pss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 采购入库单
 * @author Administrator
 *
 */
@Getter
@Setter@Mark("采购入库单")
public class StockIncomeBill extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	public static final int NORMAL = 0;
	public static final int AUDIT = 1;

	private String sn;
	private Date vdate;
	private Depot depot;
	private Supplier supplier;
	private BigDecimal totalNum;
	private BigDecimal totalAmount;
	private Employee inputUser;
	private Date inputTime;
	private Employee auditor;
	private Date auditTime;
	private int status = NORMAL;
	private List<StockIncomeBillItem> items=new ArrayList<>();
	
	public String getStatusDisplay() {
		return status == NORMAL ? "正常" : "已审核";
	}
}
