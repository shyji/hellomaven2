<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>小码哥PSS（演示版）</title>
	<link href="/style/main_css.css" rel="stylesheet" type="text/css" />
	<link href="/style/zTreeStyle.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript" src="/js/plugins/zTree/js/jquery.ztree.core-3.4.min.js"></script>
	<%--<script type="text/javascript" src="/js/system/index2.js"></script>--%>
</head>
<body>
    <div id="top">
		<div id="top_logo">
			<img alt="logo" src="/images/common/logo.jpg" width="274" height="49" style="vertical-align:middle;">
		</div>
		<div id="top_links">
			<div id="top_op">
				<ul>
					<li>
						<img alt="当前用户" src="/images/common/user.jpg">：
						<span><s:property value="#session.USER_IN_SESSION.name" /></span>
					</li>
					<li>
						<img alt="今天是" src="/images/common/date.jpg">：
						<span id="day_day"></span>
					</li>
				</ul> 
			</div>
			<div id="top_close">
				<a href="/login.jsp" target="_parent">
					<img alt="退出系统" title="退出系统" src="/images/common/close.jpg" style="position: relative; top: 10px; left: 25px;">
				</a>
			</div>
		</div>
	</div>
    <!-- side menu start -->
	<script type="text/javascript">
        var datas={
            "business":[{id:1,pId:0,title:"业务模块",sn:"business",isParent:true}],
            "systemManage":[{id:1,pId:0,title:"系统管理",sn:"systemManage",isParent:true}],
            "charts":[{id:1,pId:0,title:"系统报表",sn:"charts",isParent:true}]
        }
        var setting = {
            async:{
                enable:true,
                url:"systemMenu_loadCurrentEmployeeMenus.action",
                autoParam:["sn=qo.parentSn"]
            },
            callback: {
                onClick: function(event,treeId,treeNode){
                    if(treeNode.action){
                        $("#rightMain").prop("src",treeNode.action+".action");
                        $("#here_area").html("当前位置：系统&nbsp;>&nbsp;"+treeNode.title);
                    }
                }
            },
            data: {
                key:{
                    name:"title",
                    title:"title"
                },
                simpleData: {
                    enable: true,
                }
            }
        };
        function loadMenu(sn){
            $.fn.zTree.init($("#dleft_tab1"),setting,datas[sn]);
		}
		$(function(){
            loadMenu("business");
		    $("#TabPage2 li").click(function(){
		        $("#TabPage2 li").removeClass("selected").each(function(index,item){
		            $(item).children("img").prop("src","/images/common/"+(index+1)+".jpg");
				})
				$(this).addClass("selected").children("img").prop("src","/images/common/"+($(this).index()+1)+"_hover.jpg");
		        $("#nav_module img").prop("src","/images/common/module_"+($(this).index()+1)+".png");
                loadMenu($(this).data("rootmenu"));
			});




		})

	</script>
	<div id="side">
		<div id="left_menu">
		 	<ul id="TabPage2" style="height:200px; margin-top:50px;">
				<li id="left_tab1" class="selected" title="业务模块" data-rootmenu="business">
					<img alt="业务模块" title="业务模块" src="/images/common/1_hover.jpg" width="33" height="31">
				</li>
				<li id="left_tab2" title="系统管理" data-rootmenu="systemManage">
					<img alt="系统管理" title="系统管理" src="/images/common/2.jpg" width="33" height="31">
				</li>		
				<li id="left_tab3" title="报表" data-rootmenu="charts">
					<img alt="报表" title="报表" src="/images/common/3.jpg" width="33" height="31">
				</li>
			</ul>
			
			<div id="nav_show" style="position:absolute; bottom:0px; padding:10px;">
				<a href="javascript:;" id="show_hide_btn">
					<img alt="显示/隐藏" title="显示/隐藏" src="/images/common/nav_hide.png" width="35" height="35">
				</a>
			</div>
		 </div>
		 <div id="left_menu_cnt">
		 	<div id="nav_module">
		 		<img src="/images/common/module_1.png" width="210" height="58"/>
		 	</div>
		 	<div id="nav_resource">
		 		<ul id="dleft_tab1" class="ztree">
		 		</ul>
		 	</div>
		 </div>
	</div>
    <!-- side menu start -->
    <div id="top_nav">
	 	<span id="here_area">当前位置：系统&nbsp;>&nbsp;系统介绍</span>
	</div>
    <div id="main">
      	<iframe name="right" id="rightMain" src="" frameborder="no" scrolling="auto" width="100%" height="100%" allowtransparency="true"/>
    </div>
</body>
</html>
   
 