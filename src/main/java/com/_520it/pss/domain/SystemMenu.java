package com._520it.pss.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统菜单
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter@Mark("系统菜单")
public class SystemMenu extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;// 菜单名称
	private String action;// 菜单连接;
	private String sn;// 菜单编号
	private SystemMenu parent;// 父级菜单;

	public String getParentDisplay() {
		return parent == null ? "根目录" : parent.getTitle();
	}

	public List<SystemMenu> getAllParents() {
		List<SystemMenu> list = new ArrayList<>();
		if(this.getId()!=null)
		list.add(this);
		if (this.getParent() != null) {
			this.listParents(this.getParent(), list);
		}
		Collections.reverse(list);
		return list;
	}

	private void listParents(SystemMenu parent, List<SystemMenu> list) {
		list.add(parent);
		if (parent.getParent() != null) {
			listParents(parent.getParent(), list);
		}
	}

	@Override
	public String toString() {
		return "SystemMenu [title=" + title + ", action=" + action + ", sn="
				+ sn + "]";
	}

	public Map<String, Object> toJsonMap() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("title", title);
		m.put("action", action);
		return m;
	}

}
