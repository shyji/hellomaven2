<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script src="/js/highcharts/js/highcharts.js"></script>
    <script src="/js/highcharts/js/modules/exporting.js"></script>

    <title>PSS-采购订单图表</title>
    <script>

        $(function () {
            var group={"month":"月份分组","employee":"按售货员分组","brand":"品牌"};
            $('#container').highcharts({
                title: {
                    text: '销售报表图示',
                    x: -20 //center
                },
                subtitle: {
                    text: group['<s:property value="qo.group"/>'],
                    x: -20
                },
                xAxis: {
                    categories: <s:property value="#categories" escapeHtml="false"/>
                },
                yAxis: {
                    title: {
                        text: '销售额 (￥)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valuePrefix: '￥'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',


                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '销售额',
                    data: <s:property value="#datas" escapeHtml="false"/>
                }]
            });
        });
    </script>
</head>
<body>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
