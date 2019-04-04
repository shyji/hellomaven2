package com._520it.pss.dao.impl;

import com._520it.pss.dao.IChartDAO;
import com._520it.pss.query.OrderChartQueryObject;
import com._520it.pss.vo.OrderChartVO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Locale;

public class ChartDAOImpl implements IChartDAO {
    @Setter
    private SessionFactory sessionFactory;
    enum GroupType{
        EMPLOYEE("obj.bill.inputUser.name","obj.bill.inputUser"),
        PRODUCT("obj.product.name","obj.product"),
        BRAND("obj.product.brand.name","obj.product.brand"),
        MONTH("DATE_FORMAT(obj.bill.vdate,'%Y-%m')","DATE_FORMAT(obj.bill.vdate,'%Y-%m')"),
        DAY("DATE_FORMAT(obj.bill.vdate,'%Y-%m-%d')","DATE_FORMAT(obj.bill.vdate,'%Y-%m-%d')");

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
    public List<OrderChartVO> query(OrderChartQueryObject qo) {
        GroupType groupType =GroupType.valueOf(qo.getGroup().toUpperCase(Locale.ENGLISH));
        System.out.println(groupType.getGroupValue());
        System.out.println(groupType.getGroupBy());
        StringBuilder sb = new StringBuilder(80);
        sb.append("select new OrderChartVO("+groupType.getGroupValue()+",sum(obj.number),sum(obj.totalAmount)) ").append(" from OrderBillItem obj ").append(qo.getQuery()).append(" group by ").append(groupType.groupBy);

        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.createQuery(sb.toString()).setProperties(qo.getParameterMap()).list();

    }


    

}
