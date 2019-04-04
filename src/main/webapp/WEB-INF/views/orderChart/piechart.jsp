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

    <title>PSS-采购饼图表</title>
    <script>

        $(function () {
            var group={"month":"按月份分组","employee":"按售货员分组","brand":"品牌","client":"按客户分组"};
            $('#container').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: 1,//null,
                    plotShadow: false
                },
                title: {
                    text: group['<s:property value="qo.group"/>']+'销售额饼图'
                },
                tooltip: {
                    pointFormat: '{series.name}{point.value}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '占总销售额',
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
