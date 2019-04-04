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
        $(function(){
            $(".beginDate").click(function(){

                WdatePicker({
                    skin:"twoer",
                    maxDate:$(".endDate").val() || new Date()
                });
            }); $(".endDate").click(function(){

                WdatePicker({
                    skin:"twoer",
                    maxDate:new Date(),
                    minDate:$(".beginDate").val()
                });
            });
        })
    </script>
    <title>PSS-销售出库单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>


<s:form id="searchForm" action="stockOutcomeBill" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qo.beginDate" format="yyyy-MM-dd" var="_beginDate"/>
                        <s:textfield name="qo.beginDate" cssClass="Wdate ui_input_txt02 beginDate" value="%{#_beginDate}"/>
                        ~
                        <s:date name="qo.endDate" format="yyyy-MM-dd" var="_endDate"/>
                        <s:textfield name="qo.endDate" cssClass="Wdate ui_input_txt02 endDate" value="%{#_endDate}"/>
                        供应商
                        <s:select list="#clients" name="qo.clientId" listKey="id" listValue="name" headerKey="-1" headerValue="--全部--" cssClass="ui_select01"/>
                        状态
                        <s:select name="qo.status" list="#{0:'正常',1:'已审核'}" headerKey="-1" headerValue="--全部--" cssClass="ui_select01" />
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url action="stockOutcomeBill_batchDelete"/>"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url action="stockOutcomeBill_input"/>"/>
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
                        <th>业务时间</th>
                        <th>客户</th>
                        <th>单据总金额</th>
                        <th>单据总数量</th>
                        <th>录入人</th>
                        <th>录入时间</th>
                        <th>审核人</th>
                        <th>审核时间</th>
                        <th>状态</th>

                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid='<s:property value="id"/>'/>
                            </td>
                            <td>
                                <s:property value="sn"/>

                            </td>
                            <td>
                                <s:property value="vdate"/>

                            </td>

                            <td>
                                <s:property value="client.name"/>

                            </td>

                            <td>
                                <s:property value="totalAmount"/>

                            </td>
                            <td>
                                <s:property value="totalNum"/>

                            </td>
                            <td>
                                <s:property value="inputUser.name"/>

                            </td>
                            <td>
                                <s:date name="inputTime" var="_inputTime" format="yyyy-MM-dd"/>
                                <s:property value="#_inputTime"/>

                            </td>

                            <td>
                                <s:property value="auditor.name"/>

                            </td>
                            <td>
                                <s:property value="auditTime"/>

                            </td>
                            <td>
                                <s:property value="statusDisplay"/>

                            </td>





















                            <td>
                                <s:a action="stockOutcomeBill_input">
                                    <s:param name="stockOutcomeBill.id" value="id"/>
                                    编辑
                                </s:a>
                                <a href="javascript:;" class="btn_delete"
                                   data-url="<s:url action="stockOutcomeBill_delete">
                                <s:param name="stockOutcomeBill.id" value="id"/>
                                </s:url>">删除</a>
                                <s:if test="status==0">
                                <s:a action="stockOutcomeBill_audit"><s:param name="stockOutcomeBill.id" value="id"/>审核</s:a>
                                </s:if>
                                <s:else>
                                    <s:a action="stockOutcomeBill_view"><s:param name="stockOutcomeBill.id" value="id"/>查看</s:a>
                                </s:else>
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
