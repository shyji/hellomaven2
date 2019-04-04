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
<s:form name="editForm" action="orderBill_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="orderBill.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">

                <tr>
                    <td class="ui_text_rt" width="140">采购订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务实际</td>
                    <td class="ui_text_lt">
                        <s:date name="orderBill.vdate" format="yyyy-MM-dd" var="_vdate"/>
                        <s:textfield name="orderBill.vdate" cssClass="Wdate ui_input_txt02" value="%{#_vdate}" onclick="WdatePicker();"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <s:select list="#suppliers" name="orderBill.supplier.id" listKey="id" listValue="name"/>
                    </td>
                </tr>

                <%--<tr>--%>
                    <%--<td class="ui_text_rt" width="140">总量</td>--%>
                    <%--<td class="ui_text_lt">--%>
                        <%--<s:textfield name="orderBill.totalNum" cssClass="ui_input_txt02"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="ui_text_rt" width="140">总计</td>--%>
                    <%--<td class="ui_text_lt">--%>
                        <%--<s:textfield name="orderBill.totalAmount" cssClass="ui_input_txt02"/>--%>
                    <%--</td>--%>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                    <td class="ui_text_lt">
                        <input type="button" class="ui_input_btn01 appendRow" value="添加明细" />
                    </td>
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

                            <s:if test="orderBill.id!=null">
                                <s:iterator value="orderBill.items">
                                    <tr>
                                        <td>
                                            <input type="text" disabled="disabled" readonly="readonly" class="ui_input_txt02" tagName="name" value='<s:property value="product.name"/>'>
                                            <img alt="点击选择货品" src="/images/common/search.png" class="searchproduct"/>
                                            <s:hidden name="orderBill.items.product.id" cssClass="ui_input_txt02" tagName="pid" value="%{product.id}"/>
                                        </td>
                                        <td><span tagName="sn"><s:property value="product.sn"/></span></td>
                                        <td><span tagName="brand"><s:property value="product.brand.name"/></span></td>
                                        <td><s:textfield name="orderBill.items.salePrice" cssClass="ui_input_txt02" tagName="salePrice" value="%{salePrice}"/></td>
                                        <td><s:textfield name="orderBill.items.number" cssClass="ui_input_txt02" tagName="number" value="%{number}"/></td>
                                        <td><span tagName="totalAmount"><s:property value="totalAmount"/></span></td>
                                        <td><s:textfield name="orderBill.items.remark" cssClass="ui_input_txt02" tagName="remark" value="%{remark}"/></td>
                                        <td>
                                            <a href="javascript:void(0);" class="removeItem">删除明细</a>

                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:if>
                            <s:else>
                                <tr>
                                    <td>
                                        <input type="text" disabled="disabled" readonly="readonly" class="ui_input_txt02" tagName="name">
                                        <img alt="点击选择货品" src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="orderBill.items.product.id" cssClass="ui_input_txt02" tagName="pid"/>

                                    </td>
                                    <td><span tagName="sn"></span></td>
                                    <td><span tagName="brand"></span></td>
                                    <td><s:textfield name="orderBill.items.salePrice" cssClass="ui_input_txt02" tagName="salePrice"/></td>
                                    <td><s:textfield name="orderBill.items.number" cssClass="ui_input_txt02" tagName="number"/></td>
                                    <td><span tagName="totalAmount"></span></td>
                                    <td><s:textfield name="orderBill.items.remark" cssClass="ui_input_txt02" tagName="remark"/></td>
                                    <td>
                                        <a href="javascript:void(0);" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </s:else>
                            </tbody>
                            <script type="text/javascript" >
                                $(function(){
                                    var cloneItem=$("#edit_table_body tr:first").clone();
                                    cloneItem.find(":input").val("");
                                    cloneItem.find("span[tagName]").empty();
                                    var OpenWindow;
                                    $(":button.appendRow").click(function(){
                                        cloneItem.clone().appendTo($("#edit_table_body"));
                                    })
                           //  $("#edit_table_body").on("click",".")
                            $("#edit_table_body").on("click",".searchproduct",function(){

                                var inputProduct=$(this);

                                OpenWindow = window.open('/product_selectProductList.action', 'newwindow', 'height=800, width=900, top=0, left=0, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=no');
                                var xunhuan = setInterval(function(){
                                    if(OpenWindow.jsonResult){
                                        var row = inputProduct.closest("tr");
                                        row.find("[tagName=pid]").val(OpenWindow.jsonResult.id);
                                        row.find("[tagName=name]").val(OpenWindow.jsonResult.name);
                                        row.find("[tagName=sn]").text(OpenWindow.jsonResult.sn);
                                        row.find("[tagName=brand]").text(OpenWindow.jsonResult.brand);
                                        row.find("[tagName=salePrice]").val(OpenWindow.jsonResult.salePrice);
                                        window.clearInterval(xunhuan);
                                        OpenWindow=null;
                                    }
                                },100);

                            }) ;
                                })

                                $(function(){
                                    $("#edit_table_body").on("click",".removeItem",function(){
                                        if($("#edit_table_body tr").length>1){
                                            $(this).closest("tr").remove();
                                        }else{
                                            $(this).closest("tr").find(":input").val("");
                                            $(this).closest("tr").find("spanp[tagName]").empty();
                                        }

                                    }).on("change","[tagName=salePrice],[tagName=number]",function(){
                                       var _row= $(this).closest("tr");
                                       var salePrice = parseFloat(_row.find("[tagName=salePrice]").val());
                                       var number = parseFloat(_row.find("[tagName=number]").val());
                                        if(salePrice && number){
                                            _row.find("[tagName=totalAmount]").text(salePrice*number.toFixed(2));
                                        }
                                    });


                                    $("#editForm").submit(function(){
                                       var i = 0;
                                        $("#edit_table_body tr").each(function(index,row){
                                            var _row =$(row);
                                            if(!_row.find("[tagName=pid]").val() || !_row.find("[tagName=salePrice]").val() || !_row.find("[tagName=number]")){
                                                _row.remove();
                                            }else{
                                                _row.find("[tagName=pid]").prop("name","orderBill.items["+i+"].product.id");
                                                _row.find("[tagName=salePrice]").prop("name","orderBill.items["+i+"].salePrice");
                                                _row.find("[tagName=number]").prop("name","orderBill.items["+i+"].number");
                                                _row.find("[tagName=remark]").prop("name","orderBill.items["+i+"].remark");
                                                i++;
                                            }
                                        });

                                    });
                                });

                            </script>
                        </table>
                    </td>
                </tr>


                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>