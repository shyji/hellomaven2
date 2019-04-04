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
    <title>PSS-系统菜单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<s:debug></s:debug>
<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>


<s:form id="searchForm" action="systemMenu" method="post">
 <s:hidden name="qo.parentId"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url action="systemMenu_batchDelete"/>"/>
                        <s:url action="systemMenu_input" var="_url">
                            <s:param name="qo.parentId" value="qo.parentId"/>
                        </s:url>

                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:property value="#_url"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <s:a action="systemMenu">根目录</s:a>
                <s:iterator value="systemMenu.allParents">
                    --><s:a action="systemMenu">
                            <s:param name="qo.parentId" value="id"></s:param><s:property value="title"/>
                      </s:a>
                </s:iterator>
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>系统菜单编号</th>
                        <th>系统菜单名称</th>
                        <th>系统菜单路径</th>
                        <th>父级菜单</th>
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

                            <td>
                                <s:property value="sn"/>

                            </td>
                            <td>
                                <s:property value="title"/>

                            </td>
                            <td>
                                <s:property value="action"/>

                            </td>


                            <td>
                                <s:property value="parentDisplay"/>

                            </td>


                            <td>
                                <s:a action="systemMenu_input">
                                    <s:param name="systemMenu.id" value="id"/>
                                    编辑
                                </s:a>
                                <a href="javascript:;" class="btn_delete"
                                   data-url="<s:url action="systemMenu_delete">
                                <s:param name="systemMenu.id" value="id"/>
                                </s:url>">删除</a>
                                <s:a action="systemMenu">
                                    <s:param name="qo.parentId" value="id"/>
                                    查看下一级
                                </s:a>

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
