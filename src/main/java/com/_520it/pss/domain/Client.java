package com._520it.pss.domain;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户对象
 * @author Administrator
 *
 */
@Getter
@Setter@Mark("客户对象")
public class Client extends BaseDomain {

	private String name;
	private String linkPhone;
	private String address;
}
