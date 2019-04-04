package com._520it.generator;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import com._520it.pss.uitl.Mark;
import lombok.Getter;

import com._520it.pss.domain.BaseDomain;
import lombok.ToString;

//封装了类的相关信息
@Getter@ToString
public class ClassInfo {
	private String basePackage;// 基础包名,如com._520it.pss
	private String className;// 类名,如:Employee
	private String objectName;// 类的对象名称,如:employee
	private String chineseName;
	private List<String> props = new ArrayList<>();// 封装对象中除了id之外,所有的属性名称
	

	public ClassInfo(Class<?> domainClass) {
		this.basePackage = domainClass.getPackage().getName();
		this.basePackage = this.basePackage.substring(0,
				this.basePackage.lastIndexOf("."));
		this.className = domainClass.getSimpleName();
		this.objectName = this.className.substring(0, 1).toLowerCase()
				+ this.className.substring(1, this.className.length());
		this.chineseName=domainClass.getAnnotation(Mark.class).value();


		// 封装对象中除了id之外,所有的属性名称
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(domainClass,
					BaseDomain.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				props.add(pd.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
