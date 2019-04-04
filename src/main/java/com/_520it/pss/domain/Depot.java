package com._520it.pss.domain;

import java.math.BigDecimal;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 仓库
 * @author Administrator
 *
 */
@Setter
@Getter@Mark("仓库")
public class Depot extends BaseDomain {

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String location;
	private BigDecimal maxCapacity;
}
