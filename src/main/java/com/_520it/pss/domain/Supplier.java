package com._520it.pss.domain;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 供应商
 * @author Administrator
 *
 */
@Getter
@Setter@Mark("供应商")
public class Supplier extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String linkName="♚";
	private String linkPhone;
	private String address;
}
