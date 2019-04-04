package com._520it.pss.test;

import com._520it.pss.domain.Employee;
import com._520it.pss.domain.OrderBill;
import com._520it.pss.domain.OrderBillItem;
import com._520it.pss.domain.SystemMenu;
import com._520it.pss.query.OrderChartQueryObject;
import com._520it.pss.query.SaleChartQueryObject;
import com._520it.pss.service.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)@ContextConfiguration("classpath:applicationContext.xml")
public class TestApp {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemMenuService systemMenuService;
    @Autowired
    private IOrderBillService orderBillService;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private IChartService chartService;
    @Autowired
    private ISaleAccountService saleAccountService;
    @Test
    public void test6(){
        Employee e = new Employee();
        e.setName("jining");
        e.setAge(18);
        e.setPassword("2");
        employeeService.save(e);
    }
    @Test
    public void test26(){

        permissionService.reload();
        
    }
    
    @Test
    public void test36(){
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setParent(new SystemMenu());
        systemMenu.setAction("employee");
        systemMenuService.save(systemMenu);
        
    }
    @Test
    public void test45(){
        System.out.println(systemMenuService.queryMenuBySn("business"));
        
        
    }

    @Test
    public void test52(){
      OrderBill bill = new OrderBill();
      bill.setSn("00001");

        OrderBillItem item1 = new OrderBillItem();item1.setRemark("hello2");
        OrderBillItem item2 = new OrderBillItem();item2.setRemark("hello1");
        OrderBillItem item3 = new OrderBillItem();item2.setRemark("hello3");
        bill.getItems().add(item1);
        bill.getItems().add(item2);
        bill.getItems().add(item3);

        orderBillService.save(bill);
    }
    
    @Test
    public void test74(){
        Session session =   sessionFactory.openSession();
        session.getTransaction().begin();
        OrderBill bill = (OrderBill) session.get(OrderBill.class,1L);
       List< OrderBillItem>  items = bill.getItems();
        bill.getItems().clear();
        session.update(bill);
        session.getTransaction().commit();
        session.close();
        for(OrderBillItem item :items){
            System.out.println(item.getBill().getId());
        }
    }
    
    @Test
    public void test94(){
        OrderChartQueryObject qo = new OrderChartQueryObject();
        qo.setGroup("day");
        System.out.println(chartService.query(qo));
        
    }
    
    @Test
    public void test105(){
        SaleChartQueryObject qo = new SaleChartQueryObject();
        System.out.println(saleAccountService.querySaleChart(qo));
        

    }

}
