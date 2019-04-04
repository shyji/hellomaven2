package com._520it.pss.web.action;

import com._520it.pss.query.OrderChartQueryObject;
import com._520it.pss.query.SaleChartQueryObject;
import com._520it.pss.service.*;
import com._520it.pss.vo.OrderChartVO;
import com._520it.pss.vo.SaleChartVO;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;

public class OrderChartAction extends BaseAction{
    @Setter
    private IChartService chartService;
    @Setter
    private ISaleAccountService saleAccountService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IBrandService brandService;
    @Setter
    private IClientService clientService;
    @Setter@Getter
    private OrderChartQueryObject qoO=new OrderChartQueryObject();
    @Setter@Getter
    private SaleChartQueryObject qo = new SaleChartQueryObject();

    @Override
    public String execute() throws Exception {
        List<OrderChartVO> list = chartService.query(qoO);
        ActionContext.getContext().put("list",list);
        ActionContext.getContext().put("suppliers",supplierService.list());
        ActionContext.getContext().put("brands",brandService.list());
        return "list";
    }
    public String sale() throws Exception {
        List<SaleChartVO> list = saleAccountService.querySaleChart(qo);
        ActionContext.getContext().put("list",list);
        ActionContext.getContext().put("clients",clientService.list());
        ActionContext.getContext().put("brands",brandService.list());
        return "sale";
    }
    public String salechart() throws Exception {
        List<SaleChartVO> list = saleAccountService.querySaleChart(qo);
        List<String> categories=new ArrayList<>();
        List<BigDecimal> datas=new ArrayList<>();
        for(SaleChartVO vo:list){
            categories.add(vo.getGroupValue());
            datas.add(vo.getOrderAmount());
        }
        ActionContext.getContext().put("categories",com.alibaba.fastjson.JSON.toJSONString(categories));
        ActionContext.getContext().put("datas",com.alibaba.fastjson.JSON.toJSONString(datas));
        return "salechart";
    }
    public String piechart() throws Exception {
        List<SaleChartVO> list = saleAccountService.querySaleChart(qo);

        List<List<Object>> datas=new ArrayList<>();
        for(SaleChartVO vo:list){
            List<Object> rows = new ArrayList<>();
            rows.add(vo.getGroupValue());
            rows.add(vo.getOrderAmount());
            datas.add(rows);
        }
        ActionContext.getContext().put("datas",com.alibaba.fastjson.JSON.toJSONString(datas));
        return "piechart";
    }




}
