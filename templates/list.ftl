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
    <title>PSS-${chineseName}管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>



<s:form id="searchForm" action="${objectName}" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url action="${objectName}_batchDelete"/>"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url action="${objectName}_input"/>"/>
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
                        <#list props as pName>
                            <th>${pName}</th>
                        </#list>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid='<s:property value="id"/>'/>
                            </td>
                            <td>
                                <s:property value="id"/>
                            </td>
                             <#list props as pName>
                                 <td>
                                     <s:property value="${pName}"/>

                                 </td>

                             </#list>



                            <td>
                                <s:a action="${objectName}_input">
                                    <s:param name="${objectName}.id" value="id"/>
                                    编辑
                                </s:a>
                                <a href="javascript:;" class="btn_delete"
                                   data-url="<s:url action="${objectName}_delete">
                                <s:param name="${objectName}.id" value="id"/>
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
    