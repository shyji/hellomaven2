<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
	<link href="/style/common_style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/js/plugins/fancy-box/jquery.fancybox.css?v=2.1.5" media="screen" />
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/js/plugins/fancy-box/jquery.fancybox.pack.js?v=2.1.5"></script>
	
	<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript">
		var jsonResult=null;
	$(function(){
		$(".fancybox-effects-b").fancybox({
			openEffect  : 'none',
			closeEffect	: 'none',

			helpers : {
				title : {
					type : 'over'
				}
			}
		});
		
		$(".selectProduct").click(function(){
            jsonResult=$(this).data("json");
			window.close();
		});
	});
	</script>
<title>PSS-选择货品</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<%@ include  file="/WEB-INF/views/common/common_msg.jsp"%>
	<s:form id="searchForm" action="product_selectProductList" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							关键字
							<s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
							品牌
							<s:select cssClass="ui_select01" list="#brands" name="qo.brandId" listKey="id" listValue="name" headerKey="-1" headerValue="--全部--" />
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
							<th>pic</th>
							<th>货品编号</th>
							<th>货品名称</th>
							<th>品牌</th>
							<th>默认成本价</th>
							<th>默认销售价</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td>
										<a class="fancybox-effects-b" href='<s:property value="pic"/>' title='<s:property value="intro"/>'>
											<img class="list_img" src='<s:property value="smallPic"/>' alt="" />
										</a>
									</td>
									<td><s:property value="sn"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="brand.name"/></td>
									<td><s:property value="costPrice"/></td>
									<td><s:property value="salePrice"/></td>
									<td>
										<a class="selectProduct" href="javascript:void(0);" data-json='<s:property value="jsonString" />'>选择货品</a>
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
</body>
</html>
