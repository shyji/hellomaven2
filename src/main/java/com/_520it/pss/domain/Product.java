package com._520it.pss.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com._520it.pss.uitl.FileUploadUtil;
import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 商品管理
 * 
 * @author Administrator
 */
@Getter
@Setter@Mark("商品")
public class Product extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
	private String pic;
	private BigDecimal costPrice;
	private BigDecimal salePrice;
	private String intro;
	private Brand brand;



	public String getJsonString() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", getId());
		json.put("name", name);
		json.put("sn", sn);
		json.put("brand", brand.getName());
		json.put("costPrice", costPrice);
		json.put("salePrice", salePrice);
		return JSONObject.toJSONString(json);
	}


	public String getSmallPic() {
		if (StringUtils.hasLength(pic)) {
			return pic.substring(0, pic.indexOf(".")) + FileUploadUtil.suffix
					+ pic.substring(pic.indexOf("."));
		}
		return "";
	}

}
