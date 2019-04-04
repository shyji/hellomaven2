package com._520it.pss.dao.impl;

import com._520it.pss.dao.ISaleAccountDAO;
import com._520it.pss.domain.SaleAccount;
import com._520it.pss.query.SaleChartQueryObject;
import com._520it.pss.vo.SaleChartVO;
import lombok.Getter;
import org.hibernate.Session;

import java.util.List;
import java.util.Locale;

public class SaleAccountDAOImpl extends GenericDAOImpl<SaleAccount> implements ISaleAccountDAO{


    enum GroupType{
        EMPLOYEE("obj.saler.name","obj.saler"),
        PRODUCT("obj.product.name","obj.product"),
        BRAND("obj.product.brand.name","obj.product.brand"),
        MONTH("DATE_FORMAT(obj.vdate,'%Y-%m')","DATE_FORMAT(obj.vdate,'%Y-%m')"),
        DAY("DATE_FORMAT(obj.vdate,'%Y-%m-%d')","DATE_FORMAT(obj.vdate,'%Y-%m-%d')"),
        CLIENT("obj.client.name","obj.client");
        @Getter
        private String groupValue;
        @Getter
        private String groupBy;

        GroupType(String groupValue, String groupBy) {
            this.groupValue = groupValue;
            this.groupBy = groupBy;
        }
    }

    @Override
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        System.out.println(qo.getGroup());
        GroupType groupType = GroupType.valueOf(qo.getGroup().toUpperCase(Locale.ENGLISH));

        StringBuilder sb = new StringBuilder(80);
        sb.append("select new SaleChartVO("+groupType.getGroupValue()+",sum(obj.number),sum(obj.saleAmount),sum(obj.saleAmount)-sum(obj.costAmount)) ").append(" from SaleAccount obj ").append(qo.getQuery()).append(" group by ").append(groupType.getGroupBy());
        System.out.println(sb);
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.createQuery(sb.toString()).setProperties(qo.getParameterMap()).list();
    }


    }
