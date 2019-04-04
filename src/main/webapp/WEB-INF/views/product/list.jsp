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
    <script type="text/javascript" src="/js/plugins/fancy-box/jquery.fancybox.pack.js?v=2.1.5"></script>
    <link rel="stylesheet" type="text/css" href="/js/plugins/fancy-box/jquery.fancybox.css?v=2.1.5" media="screen" />
    <script>
        $(function () {
            $(".fancybox-effects-a").fancybox({
                helpers: {
                    title : {
                        type : 'outside'
                    },
                    overlay : {
                        speedOut : 0
                    }
                }
            });
        })
    </script>
    <title>PSS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>


<s:form id="searchForm" action="product" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        关键字
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                        品牌
                        <s:select cssClass="ui_select01" list="#brands" name="qo.brandId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="--全部--"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url action="product_batchDelete"/>"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url action="product_input"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>pic</th>
                        <th>编号</th>
                        <th>品牌</th>
                        <th>商品名称</th>
                        <th>商品介绍</th>

                        <th>默认成本价</th>

                        <th>默认销售价</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid='<s:property value="id"/>'/>
                            </td>
                            <td>
                                <a class="fancybox-effects-a" href='<s:property value="pic"/>' title="Lorem ipsum dolor sit amet, consectetur adipiscing elit">
                                    <img src='<s:property value="smallPic"/>' class="list_img"/>
                                </a>


                            </td>
                            <td>
                                <s:property value="sn"/>

                            </td>

                            <td>
                                <s:property value="brand.name"/>

                            </td>
                            <td>
                                <s:property value="name"/>

                            </td>
                            <td>
                                <s:property value="intro"/>

                            </td>

                            <td>
                                <s:property value="costPrice"/>

                            </td>
                            <td>
                                <s:property value="salePrice"/>

                            </td>

                            <td>
                                <s:a action="product_input">
                                    <s:param name="product.id" value="id"/>
                                    编辑
                                </s:a>
                                <a href="javascript:;" class="btn_delete"
                                   data-url="<s:url action="product_delete">
                                <s:param name="product.id" value="id"/>
                                </s:url>">删除</a>
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
