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
    <script src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".beginDate").click(function () {

                WdatePicker({
                    skin: "twoer",
                    maxDate: $(".endDate").val() || new Date()
                });
            });
            $(".endDate").click(function () {

                WdatePicker({
                    skin: "twoer",
                    maxDate: new Date(),
                    minDate: $(".beginDate").val()
                });
            });
            $(":input[name='qoO.group']").change(function(){
                $("#searchForm").submit();
            });

            $("#groupValue").html($(":input[name='qoO.group'] option:selected").html());

        })
    </script>
    <title>PSS-采购订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>


<s:form id="searchForm" action="orderChart" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qoO.beginDate" format="yyyy-MM-dd" var="_beginDate"/>
                        <s:textfield name="qoO.beginDate" cssClass="Wdate ui_input_txt02 beginDate"
                                     value="%{#_beginDate}"/>
                        ~
                        <s:date name="qoO.endDate" format="yyyy-MM-dd" var="_endDate"/>
                        <s:textfield name="qoO.endDate" cssClass="Wdate ui_input_txt02 endDate" value="%{#_endDate}"/>
                        货品名称
                        <s:textfield name="qoO.productName" cssClass="ui_input_txt02"/>
                        分组查询
                        <s:select list="#{'employee':'录入人','product':'货品名称','brand':'品牌','month':'月份','day':'日期'}" cssClass="ui_select01" name="qoO.group"/>
                                  供应商
                        <s:select list="#suppliers" name="qoO.supplierId" listKey="id" listValue="name" headerKey="-1" headerValue="--全部--" cssClass="ui_select01"/>
                        品牌
                        <s:select list="#brands" name="qoO.brandId" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="--全部--" cssClass="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="submit" value="查询" class="ui_input_btn01"/>

                    </div>
                </div>
            </div>
        </div>
    </div>
</s:form>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>

                        <th id="groupValue">录入人</th>
                        <th>数量</th>
                        <th>总金额</th>


                    </tr>
                    <tbody>
                    <s:iterator value="#list">
                        <tr>
                            <td>
                                <s:property value="groupValue"/>

                            </td>
                            <td>
                                <s:property value="orderNumber"/>

                            </td>

                            <td>
                                <s:property value="orderAmount"/>

                            </td>


                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>

        </div>
    </div>

</body>
</html>
