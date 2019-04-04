//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
	}
}

var datas={
		"business":[{id:1,pId:0,title:"业务模块",sn:"business",isParent:true}],
		"systemManage":[{id:1,pId:0,title:"系统管理",sn:"systemManage",isParent:true}],
		"charts":[{id:1,pId:0,title:"系统报表",sn:"charts",isParent:true}]
}

/**
 * 加载菜单树
 * @param sn
 */
function loadMenu(sn){
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
	$.fn.zTree.init($("#dleft_tab1"),setting,datas[sn]);
}

$(function(){
	loadDate();
	//加载默认的菜单树
	loadMenu("business");
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
        switchSysBar();
    });
	
	//给菜单按钮注册点击事件
	$("#TabPage2 li").click(function(){
		//还原样式
		$("#TabPage2 li").removeClass("selected").each(function(index,item){
			$(item).children("img").attr("src","/images/common/"+(index+1)+".jpg");
		})
		//给自己添加样式;
		$(this).addClass("selected").children("img").attr("src","/images/common/"+($(this).index()+1)+"_hover.jpg");
		//替换菜单图片
		$("#nav_module img").attr("src","/images/common/module_"+($(this).index()+1)+".png");
		
		//加载对应的菜单树
		loadMenu($(this).data("rootmenu"));
	});
});


























