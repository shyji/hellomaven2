package com._520it.pss.domain;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌
 * @author Administrator
 *
 */
@Getter
@Setter@Mark("品牌")
public class Brand extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
}
