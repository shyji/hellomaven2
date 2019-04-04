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
    <script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-库存明细对象管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>


<s:form id="searchForm" action="productStock" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">

                        品牌
                        <s:select cssClass="ui_select01" list="#brands" name="qo.brandId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="--全部--"/>
                        仓库
                        <s:select cssClass="ui_select01" list="#depots" name="qo.depotId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="--全部--"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>仓库</th>
                        <th>货品名称</th>
                        <th>存量</th>
                        <th>价格</th>
                        <th>总金额</th>
                        <th>最后入库时间</th>
                        <th>最后出库时间</th>

                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid='<s:property value="id"/>'/>
                            </td>
                            <td>
                                <s:property value="id"/>
                            </td>
                            <td>
                                <s:property value="depot.name"/>

                            </td>
                            <td>
                                <s:property value="product.name"/>

                            </td>
                            <td>
                                <s:property value="storeNum"/>

                            </td>
                            <td>
                                <s:property value="price"/>

                            </td>
                            <td>
                                <s:property value="amount"/>

                            </td>



                            <td>
                                <s:property value="incomeDate"/>

                            </td>

                            <td>
                                <s:property value="outcomeDate"/>

                            </td>









                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <!-- 引入分页的页面 -->
            <%@ include file="/WEB-INF/views/common/common_page.jsp" %>
        </div>
    </div>
</s:form>
</body>
</html>
