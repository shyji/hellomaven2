/** 禁用将表单元素数组或者对象做序列化* */
jQuery.ajaxSettings.traditional = true

$(function () {


    //新增按钮
    $("input.btn_input").click(function () {
        window.location.href = $(this).data("url");
    });

    //分页

    $(".btn_page").click(function () {
        var pageNo = $(this).data("page") || $(":input[name='qo.currentPage']").val();
        $(":input[name='qo.currentPage']").val(pageNo);
        $("#searchForm").submit();

    });
    $(":input[name='qo.pageSize']").change(function(){
        $(":input[name='qo.currentPage']").val(1);
        $("#searchForm").submit();
    });

})
$(function(){


    //删除
    $(".btn_delete").click(function () {

        var url = $(this).data("url");
        $.dialog({
            title: '提示',
            content: '确认删除吗?',
            icon: 'question',
            ok: function () {
                $.get(url,function(){
                    window.location.reload();
                });
            }
        });
    });
})

$(function(){
    $("#all").change(function(){
        $(":checkbox[name=IDCheck]").prop("checked",this.checked);
    });
    $(":input.btn_batchDelete").click(function(){
        var url = $(this).data("url");
        var ids=[];
        $(":checkbox[name=IDCheck]:checked").each(function(i,v){

            ids.push($(v).data("eid"));
        });
        if(ids.length==0){
            $.dialog({
                title: '提示',
                content: '请选择需要删除的数据!!!',
                icon: 'error',
                ok: true
            });
            return;
        }
        var dg= $.dialog({
            title: '提示',
            content: '数据删除中!!!',
            icon: 'face-smile'
        });
        $.post(url,{"ids":ids},function(){
            dg.content("批量删除数据成功!").button({
                name:"确认",callback:function () {
                    window.location.reload();
                }
            });
        });
    });
});
$(function(){
    var ids = $.map($(".itemSelect option"),function(item){
        return item.value;
    });

    $(".items option").each(
        function(){
            if($.inArray(this.value,ids)>=0){

                $(this).remove();
            }

        }
    );
    var eids = $.map($(".mitemSelect option"),function(item){
        return item.value;
    });

    $(".mitems option").each(
        function(){
            if($.inArray(this.value,eids)>=0){

                $(this).remove();
            }

        }
    );
    var selectO={
        select:function(){
            $(".items option:selected").appendTo($(".itemSelect"));
        },selectAll:function(){
            $(".items option").appendTo($(".itemSelect"));

        },deselect:function(){
            $(".items").append($(".itemSelect option:selected"));
        },deselectAll:function(){
            $(".items").append($(".itemSelect option"));
        }, mselect:function(){
            $(".mitems option:selected").appendTo($(".mitemSelect"));
        },mselectAll:function(){
            $(".mitems option").appendTo($(".mitemSelect"));

        },mdeselect:function(){
            $(".mitems").append($(".mitemSelect option:selected"));
        },mdeselectAll:function(){
            $(".mitems").append($(".mitemSelect option"));
        }
    };
    $(":input.left2right").click(function(){
        selectO[this.id]();
    });
    $("#editForm").submit(function(){
        $(".itemSelect option").prop("selected",true);
        $(".mitemSelect option").prop("selected",true);
    });
});
/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({
			background : "#CDDAEB"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#1D1E21"
			});
		});
	}).mouseout(function() {
		$(this).css({
			background : "#FFF"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#909090"
			});
		});
	});
});
