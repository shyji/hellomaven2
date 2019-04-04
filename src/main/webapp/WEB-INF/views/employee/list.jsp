<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
	<link href="/style/common_style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
	<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript">

	</script>
<title>PSS-账户管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>

	<%@ include  file="/WEB-INF/views/common/common_msg.jsp"%>
	<s:form id="searchForm" action="employee" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱
							<s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
							所属部门
							<s:select list="#depts" name="qo.deptId" listKey="id" listValue="name" headerKey="-1" headerValue="所有部门" cssClass="ui_select01"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete" data-url="<s:url action="employee_batchDelete"/>"/>
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="<s:url action="employee_input"/>"/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th>角色</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td><input type="checkbox" autocomplete="off" name="IDCheck" class="acb" data-eid="<s:property value="id"/>"/></td>
									<td><s:property value="id"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="email"/></td>
									<td><s:property value="age"/></td>
									<td><s:property value="dept.name"/></td>
									<td><s:property value="rolesName"/></td>
									<td>
										<s:a action="employee_input"><s:param name="employee.id" value="id"/>编辑</s:a>
										<a href="javascript:;" class="btn_delete" data-url="<s:url action="employee_delete"><s:param name="employee.id" value="id"/></s:url>">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<!-- 引入分页的页面 -->
				<%@ include  file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</s:form>
	<script type="text/javascript">
		
	</script>
</body>
</html>
    