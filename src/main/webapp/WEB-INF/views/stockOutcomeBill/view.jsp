<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
</head>
<body>
<s:actionerror/>

    
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购入库订单查看</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">

                <tr>
                    <td class="ui_text_rt" width="140">订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务实际</td>
                    <td class="ui_text_lt">
                        <s:date name="stockOutcomeBill.vdate" format="yyyy-MM-dd" var="_vdate"/>
                        <s:textfield name="stockOutcomeBill.vdate" cssClass="Wdate ui_input_txt02" value="%{#_vdate}" onclick="WdatePicker();"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.supplier.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">客户</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.client.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">总量</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.totalNum" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">总计</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.totalAmount" cssClass="ui_input_txt02"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>

                </tr>
                <tr>
                    <td class="ui_text_lt" colspan="2">
                        <table class="table edit_table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                            <tr>
                                <th>货品</th>
                                <th>货品编号</th>
                                <th>品牌</th>
                                <th>进价</th>
                                <th>数量</th>
                                <th>小计</th>
                                <th>备注</th>
                                <th></th>
                            </tr>
                            <tbody id="edit_table_body">

                            <s:if test="stockOutcomeBill.id!=null">
                                <s:iterator value="stockOutcomeBill.items">
                                    <tr>
                                        <td>
                                            <input type="text" disabled="disabled" readonly="readonly" class="ui_input_txt02" tagName="name" value='<s:property value="product.name"/>'>
                                            <img alt="点击选择货品" src="/images/common/search.png" class="searchproduct"/>
                                            <s:hidden name="stockOutcomeBill.items.product.id" cssClass="ui_input_txt02" tagName="pid" value="%{product.id}"/>
                                        </td>
                                        <td><span tagName="sn"><s:property value="product.sn"/></span></td>
                                        <td><span tagName="brand"><s:property value="product.brand.name"/></span></td>
                                        <td><s:textfield name="stockOutcomeBill.items.salePrice" cssClass="ui_input_txt02" tagName="salePrice" value="%{salePrice}"/></td>
                                        <td><s:textfield name="stockOutcomeBill.items.number" cssClass="ui_input_txt02" tagName="number" value="%{number}"/></td>
                                        <td><span tagName="totalAmount"><s:property value="totalAmount"/></span></td>
                                        <td><s:textfield name="stockOutcomeBill.items.remark" cssClass="ui_input_txt02" tagName="remark" value="%{remark}"/></td>

                                    </tr>
                                </s:iterator>
                            </s:if>

                            </tbody>

                        </table>
                    </td>
                </tr>

                <script>
                    $(":input[name]").prop("readonly",true).prop("disabled",true)
                </script>

            </table>
        </div>
    </div>

</body>
</html>